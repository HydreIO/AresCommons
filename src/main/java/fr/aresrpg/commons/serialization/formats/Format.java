package fr.aresrpg.commons.serialization.formats;

import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.types.TypeEnum;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface Format{
	void writeBegin(OutputStream out) throws IOException;
	void writeValue(OutputStream out , TypeEnum type , Object value , SerializationContext context) throws IOException;
	void writeName(OutputStream out , String name) throws IOException;
	void writeBeginObject(OutputStream out) throws IOException;
	void writeFieldSeparator(OutputStream out , boolean firstField , boolean lastField) throws IOException;
	void writeEndObject(OutputStream out) throws IOException;
	void writeEnd(OutputStream out) throws IOException;

	void read(InputStream in , Map<String , Object> container , SerializationContext context) throws IOException;
}
