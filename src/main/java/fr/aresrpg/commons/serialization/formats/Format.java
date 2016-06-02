package fr.aresrpg.commons.serialization.formats;

import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.types.TypeEnum;

import java.io.IOException;
import java.util.Map;

public interface Format<I , O>{
	void writeBegin(O out) throws IOException;
	void writeValue(O out , TypeEnum type , Object value , SerializationContext context) throws IOException;
	void writeName(O out , String name) throws IOException;
	void writeBeginObject(O out) throws IOException;
	void writeFieldSeparator(O out , boolean firstField , boolean lastField) throws IOException;
	void writeEndObject(O out) throws IOException;
	void writeEnd(O out) throws IOException;

	void read(I in , Map<String , Object> container , SerializationContext context) throws IOException;
}
