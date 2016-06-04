package fr.aresrpg.commons.serialization;

import java.io.IOException;

import fr.aresrpg.commons.serialization.formats.Format;

public interface Serializer<T, I, O> {
	void serialize(O output, T object, Format<?, O> format) throws IOException;

	T deserialize(I input, Format<I, ?> format) throws IOException;
}
