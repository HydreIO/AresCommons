package fr.aresrpg.commons.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.adapters.Adapter;
import fr.aresrpg.commons.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.serialization.field.FieldModifier;
import fr.aresrpg.commons.serialization.formats.Format;
import fr.aresrpg.commons.types.TypeEnum;
import fr.aresrpg.commons.unsafe.UnsafeAccessor;
import fr.aresrpg.commons.util.map.LinkedHashMap;

@SuppressWarnings("rawtypes")
public class BasicSerializer<T, I, O> implements Serializer<T, I, O> {

	private SerializationFactory factory;
	private Class<T> clazz;
	private Object[] fields;
	private FieldModifier fieldModifier;
	private SerializationContext context;

	public BasicSerializer(SerializationFactory factory, Class<T> clazz) {
		this.factory = factory;
		this.clazz = clazz;
		this.fieldModifier = factory.getFieldModifier();
		this.context = new BasicSerializationContext();
		setup();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void serialize(O output, T object, Format<?, O> format) throws IOException {
		TypeEnum type = TypeEnum.getType(object);
		format.writeBegin(output);
		if (type == TypeEnum.OBJECT) {
			if (!clazz.isAssignableFrom(object.getClass())) throw new IllegalArgumentException(clazz + " is not assignable from " + object.getClass());
			format.writeBeginObject(output);
			for (int i = 0; i < fields.length; i++) {
				Object field = fields[i];
				Object toSerialize;
				String name;
				if (field instanceof Field) {
					toSerialize = fieldModifier.getValue((Field) field, object);
					name = ((Field) field).getName();
				} else {
					toSerialize = ((AdaptedField) field).getValue(fieldModifier, object);
					name = ((AdaptedField) field).getField().getName();
				}
				type = TypeEnum.getType(toSerialize);
				if (type == TypeEnum.OBJECT) factory.createOrGetSerializer((Class) toSerialize.getClass()).serialize(output, toSerialize, format);
				else {
					format.writeValue(output, name, type, toSerialize, context);
					format.writeFieldSeparator(output, i == 0, i == fields.length - 1);
				}
			}
			format.writeEndObject(output);
		} else format.writeValue(output, null, type, object, context);
		format.writeEnd(output);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T deserialize(I input, Format<I, ?> format) throws IOException {
		try {
			T instance = (T) UnsafeAccessor.getUnsafe().allocateInstance(clazz);
			Map<String, Object> map = new LinkedHashMap<>();
			format.read(input, map, context);
			for (int i = 0; i < fields.length; i++) {
				Object field = fields[i];
				if (field instanceof Field) {
					Object value = map.get(((Field) field).getName());
					if (value != null) fieldModifier.setValue((Field) field, instance, value);
				} else ((AdaptedField) field).setValue(fieldModifier, instance, map.get(((AdaptedField) field).getField().getName()));
			}
			return instance;
		} catch (InstantiationException e) {
			Logger.MAIN_LOGGER.severe("BasicDeserializer", e, "Could'not create instance");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private void setup() {
		List<Object> f = new ArrayList<>();

		Field[] fds = clazz.getDeclaredFields();
		for (int i = clazz.isMemberClass() ? 1 : 0; i < fds.length; i++) {
			Field field = fds[i];
			if (!processField(field) || !fieldModifier.canProcess(field)) continue;
			fieldModifier.preprocess(field);
			Adapter last = factory.getAdapter(new ParametrizedClass<>(field.getType()));
			if (last != null) {
				List<Adapter> adapters = new ArrayList<>();
				adapters.add(last);
				while (last != null) {
					last = factory.getAdapter(last.getOutType());
					adapters.add(last);
				}
				f.add(new AdaptedField(adapters.toArray(new Adapter[adapters.size()]), field));
			} else f.add(field);
		}
		this.fields = f.toArray(new Object[f.size()]);
	}

	protected boolean processField(Field f) {
		return !Modifier.isStatic(f.getModifiers()) && !Modifier.isTransient(f.getModifiers());
	}

	public static class AdaptedField {
		private Adapter[] adapters;
		private Field field;

		public AdaptedField(Adapter[] adapters, Field field) {
			this.adapters = adapters;
			this.field = field;
		}

		@SuppressWarnings("unchecked")
		public Object getValue(FieldModifier modifier, Object instance) {
			Object o = modifier.getValue(field, instance);
			for (Adapter adapter : adapters)
				o = adapter.adaptTo(o);
			return o;
		}

		@SuppressWarnings("unchecked")
		public void setValue(FieldModifier modifier, Object instance, Object value) {
			if (value == null) return;
			Object o = value;
			for (Adapter adapter : adapters)
				o = adapter.adaptFrom(o);
			modifier.setValue(field, instance, o);
		}

		public Field getField() {
			return field;
		}
	}

	private class BasicSerializationContext implements SerializationContext {

		@Override
		@SuppressWarnings("unchecked")
		public <E> void serialize(OutputStream stream, E value, Format format) throws IOException {
			factory.createOrGetSerializer((Class<E>) value.getClass()).serialize(stream, value, format);
		}
	}
}
