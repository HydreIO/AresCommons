package fr.aresrpg.commons.infra.serialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.reflection.ParametrizedClass;
import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.adapters.Adapter;
import fr.aresrpg.commons.domain.serialization.annotations.SerializedName;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.domain.serialization.field.FieldModifier;
import fr.aresrpg.commons.domain.serialization.formats.Format;
import fr.aresrpg.commons.domain.types.TypeEnum;
import fr.aresrpg.commons.domain.unsafe.UnsafeAccessor;
import fr.aresrpg.commons.domain.util.map.LinkedHashMap;

@SuppressWarnings("rawtypes")
public class BasicSerializer<T, I, O> implements Serializer<T, I, O> {

	private SerializationFactory factory;
	private Class<T> clazz;
	private AdaptedField[] fields;
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
				AdaptedField field = fields[i];
				Object toSerialize = field.getValue(fieldModifier, object);
				type = TypeEnum.getType(toSerialize);
				format.writeValue(output, getName(field.getField()), type, toSerialize, context);
				format.writeFieldSeparator(output, i == 0, i == fields.length - 1);
			}
			format.writeEndObject(output);
		} else format.writeValue(output, null, type, object, context);
		format.writeEnd(output);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T deserialize(I input, Format<I, ?> format) throws IOException {
		Map<String, Object> map = new LinkedHashMap<>();
		format.read(input, map, context);
		return deserialize(map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T deserialize(Map<String, Object> map) throws IOException {
		try {
			T instance = (T) UnsafeAccessor.getUnsafe().allocateInstance(clazz);
			for (int i = 0; i < fields.length; i++) {
				AdaptedField field = fields[i];
				Object value = map.get(getName(field.getField()));
				if (value != null) {
					Class<?> c = field.getInType().getRaw();
					if (value instanceof Map && !Map.class.isAssignableFrom(c)) value = factory.createOrGetSerializer(c).deserialize((Map<String, Object>) value);

					field.setValue(fieldModifier, instance, value);
				}
			}
			return instance;
		} catch (InstantiationException e) {
			Logger.MAIN_LOGGER.severe("BasicDeserializer", e, "Could'not create instance");
			return null;
		}
	}

	protected boolean processField(Field f) {
		return !Modifier.isStatic(f.getModifiers()) && !Modifier.isTransient(f.getModifiers());
	}

	protected String getName(Field f) {
		SerializedName name = f.getAnnotation(SerializedName.class);
		if (name != null) return name.value();
		else return f.getName();
	}

	@SuppressWarnings("unchecked")
	private void setup() {
		List<AdaptedField> f = new ArrayList<>();

		Field[] fds = clazz.getDeclaredFields();
		for (int i = (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) ? 1 : 0; i < fds.length; i++) {
			Field field = fds[i];
			if (!processField(field) || !fieldModifier.canProcess(field)) continue;
			fieldModifier.preprocess(field);
			Adapter last = factory.getAdapter(new ParametrizedClass<>(field.getType()));
			if (last != null) {
				List<Adapter> adapters = new ArrayList<>();
				while (last != null) {
					adapters.add(last);
					last = factory.getAdapter(last.getOutType());
				}
				f.add(new AdaptedField(adapters.toArray(new Adapter[adapters.size()]), field));
			} else f.add(new AdaptedField(new Adapter[0], field));
		}
		this.fields = f.toArray(new AdaptedField[f.size()]);
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
			if(adapters.length > 0)
				for (int i = adapters.length; i >= 0; i--)
					o = adapters[i].adaptFrom(o);
			modifier.setValue(field, instance, o);
		}

		public Field getField() {
			return field;
		}

		public ParametrizedClass<?> getInType() {
			if (adapters.length == 0) return new ParametrizedClass<>(field.getGenericType());
			else return adapters[adapters.length - 1].getOutType();
		}
	}

	private class BasicSerializationContext implements SerializationContext<I, O> {

		@Override
		@SuppressWarnings("unchecked")
		public <E> void serialize(O stream, E value, Format format) throws IOException {
			factory.createOrGetSerializer(value.getClass()).serialize(stream, value, format);
		}
	}
}
