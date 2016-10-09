package fr.aresrpg.commons.domain.serialization;

import java.io.IOException;
import java.util.Map;

/**
 * A serialization context
 *
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface SerializationContext {
	/**
	 * Serialize this value using the provided format
	 *
	 * @param out
	 *            the output
	 * @param value
	 *            the value to serialize
	 * @param format
	 *            the format to use
	 * @param <T>
	 *            the type of the value
	 * @param <O>
	 *            the out
	 * @throws IOException
	 *             when the output writing fails
	 */
	<T, O> void serialize(O out, T value, Format<?, O> format) throws IOException;
}
