package fr.aresrpg.commons.serialization.adapters;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.DeserializationContext;
import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.serialization.formats.DeserializationFormat;
import fr.aresrpg.commons.serialization.formats.SerializationFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClassAdapter implements Adapter<Class<?>> {
	public static final ParametrizedClass<Class<?>> TYPE = new ParametrizedClass<Class<?>>(){};

	@Override
	public void adaptTo(OutputStream outputStream, Class<?> object, SerializationContext context, SerializationFormat format) throws IOException {
		format.writeString(outputStream , object.getName(), context);
	}

	@Override
	public Class<?> adaptFrom(InputStream inputStream, DeserializationContext context, DeserializationFormat format) {
		return null;
	}

	@Override
	public ParametrizedClass<Class<?>> getType() {
		return TYPE;
	}
}
