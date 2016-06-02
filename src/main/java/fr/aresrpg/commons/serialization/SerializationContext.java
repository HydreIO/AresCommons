package fr.aresrpg.commons.serialization;

import fr.aresrpg.commons.serialization.formats.Format;

import java.io.IOException;
import java.io.OutputStream;

public interface SerializationContext {
	<T> void serialize(OutputStream stream , T value , Format format) throws IOException;
}
