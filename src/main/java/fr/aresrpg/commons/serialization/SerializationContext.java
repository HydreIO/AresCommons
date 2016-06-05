package fr.aresrpg.commons.serialization;

import java.io.IOException;

import fr.aresrpg.commons.serialization.formats.Format;

public interface SerializationContext<I, O> {
	@SuppressWarnings("rawtypes")
	<T> void serialize(O out, T value, Format format) throws IOException;
}
