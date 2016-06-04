package fr.aresrpg.commons.serialization;

import java.io.IOException;
import java.io.OutputStream;

import fr.aresrpg.commons.serialization.formats.Format;

public interface SerializationContext<I , O> {
	@SuppressWarnings("rawtypes")
	<T> void serialize(O out, T value, Format format) throws IOException;
}
