package fr.aresrpg.commons.serialization;

import java.io.IOException;
import java.io.OutputStream;

import fr.aresrpg.commons.serialization.formats.Format;

public interface SerializationContext {
	@SuppressWarnings("rawtypes")
	<T> void serialize(OutputStream stream, T value, Format format) throws IOException;
}
