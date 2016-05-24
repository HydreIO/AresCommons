package fr.aresrpg.commons.serialization.adapters;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.DeserializationContext;
import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.serialization.formats.DeserializationFormat;
import fr.aresrpg.commons.serialization.formats.SerializationFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Adapter<T> {
	void adaptTo(OutputStream outputStream , T object , SerializationContext context , SerializationFormat format) throws IOException;
	T adaptFrom(InputStream inputStream , DeserializationContext context , DeserializationFormat format);
	ParametrizedClass<T> getType();
}
