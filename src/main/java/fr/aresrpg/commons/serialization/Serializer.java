package fr.aresrpg.commons.serialization;

import fr.aresrpg.commons.serialization.formats.Format;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface Serializer<T> {
	void serialize(OutputStream output , T object , Format format) throws IOException;
	T deserialize(InputStream input, Format format) throws IOException;
}
