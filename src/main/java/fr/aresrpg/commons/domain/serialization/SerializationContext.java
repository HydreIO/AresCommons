package fr.aresrpg.commons.domain.serialization;

import java.io.IOException;

import fr.aresrpg.commons.domain.serialization.formats.Format;

public interface SerializationContext<I , O> {
	@SuppressWarnings("rawtypes")
	<T> void serialize(O out, T value, Format format) throws IOException;
}
