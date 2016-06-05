package fr.aresrpg.commons.domain.serialization;

import java.io.IOException;
import java.util.Map;

import fr.aresrpg.commons.domain.serialization.formats.Format;

public interface Serializer<T, I, O> {
	void serialize(O output, T object, Format<?, O> format) throws IOException;

	T deserialize(I input, Format<I, ?> format) throws IOException;
	T deserialize(Map<String , Object> values) throws IOException;
}
