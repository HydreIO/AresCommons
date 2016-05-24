package fr.aresrpg.commons.serialization;

import fr.aresrpg.commons.reflection.ParametrizedClass;

import java.io.IOException;
import java.io.OutputStream;

public interface SerializationContext {
	<T> void serialize(OutputStream stream , T value , ParametrizedClass<T> clazz) throws IOException;
	<T> void serializeObject(OutputStream stream , T value , ParametrizedClass<T> clazz) throws IOException;
	void deepSerialize(OutputStream stream , Object value) throws IOException;
}
