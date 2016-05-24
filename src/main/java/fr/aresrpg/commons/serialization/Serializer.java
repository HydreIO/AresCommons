package fr.aresrpg.commons.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.adapters.Adapter;
import fr.aresrpg.commons.serialization.annotations.SerializedName;
import fr.aresrpg.commons.serialization.field.FieldModifier;
import fr.aresrpg.commons.serialization.field.FieldNamer;
import fr.aresrpg.commons.serialization.formats.SerializationFormat;

@SuppressWarnings("rawtypes")
public class Serializer {
	private SerializationFormat format;
	private Collection<Adapter> adapters;
	private FieldNamer namer;
	private FieldModifier modifier;
	private SerializationContext context = new InternalSerializationContext();

	public Serializer(SerializationFormat format, Collection<Adapter> adapters, FieldNamer namer, FieldModifier modifier) {
		this.format = format;
		this.adapters = adapters;
		this.namer = namer;
		this.modifier = modifier;
	}

	public <T> void serialize(OutputStream stream, T value) throws IOException {
		serialize(stream, value, value == null ? null : new ParametrizedClass<T>(value.getClass()));
	}

	public <T> void serialize(OutputStream stream, T value, ParametrizedClass<T> clazz) throws IOException {
		Adapter<T> adapter = getAdapter(clazz);

		if (adapter != null) adapter.adaptTo(stream, value, context, format);
		else format.write(stream, value, context);// If format call serialize deepSerialize
	}

	protected <T> void serializeObject(OutputStream stream, T value, ParametrizedClass<T> clazz) throws IOException {
		Adapter<T> adapter = getAdapter(clazz);

		if (adapter != null) adapter.adaptTo(stream, value, context, format);
		else format.writeObject(stream, value, context);// If format call serialize deepSerialize
	}

	protected void deepSerialize(OutputStream stream, Object value) throws IOException {
		Class<?> current = value.getClass();
		while (current != null) {
			Field[] fields = current.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (processField(field) && modifier.canProccess(field)) {
					String name = namer.transform(getName(field));
					format.writeField(stream, name, modifier.getValue(field, value), context, i == fields.length - 1);
				}
			}
			current = current.getSuperclass();
		}
	}

	protected String getName(Field field) {
		if (field.getAnnotation(SerializedName.class) != null) return field.getAnnotation(SerializedName.class).value();
		else return field.getName();
	}

	protected boolean processField(Field f) {
		return !Modifier.isStatic(f.getModifiers()) && !Modifier.isTransient(f.getModifiers());
	}

	@SuppressWarnings("unchecked")
	public <T> Adapter<T> getAdapter(ParametrizedClass<T> clazz) {
		if (adapters.isEmpty()) return null;
		for (Adapter adapter : adapters)
			if (clazz.equals(adapter.getType())) return adapter;
		return null;
	}

	private class InternalSerializationContext implements SerializationContext {
		@Override
		public <T> void serialize(OutputStream stream, T value, ParametrizedClass<T> clazz) throws IOException {
			Serializer.this.serialize(stream, value, clazz);
		}

		@Override
		public <T> void serializeObject(OutputStream stream, T value, ParametrizedClass<T> clazz) throws IOException {
			Serializer.this.serializeObject(stream, value, clazz);
		}

		@Override
		public void deepSerialize(OutputStream stream, Object value) throws IOException {
			Serializer.this.deepSerialize(stream, value);
		}
	}
}
