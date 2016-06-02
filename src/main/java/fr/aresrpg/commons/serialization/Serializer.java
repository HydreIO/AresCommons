package fr.aresrpg.commons.serialization;

import fr.aresrpg.commons.serialization.formats.Format;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface Serializer<T , I , O> {
	void serialize(O output , T object , Format<? , O> format) throws IOException;
	T deserialize(I input, Format<I , ?> format) throws IOException;
}
