package fr.aresrpg.commons.domain.serialization;

import fr.aresrpg.commons.domain.serialization.formats.Format;

import java.io.IOException;

/**
 * A serialization context
 * 
 * @param <I>
 *            the input type
 * @param <O>
 *            the output type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface SerializationContext<O> {
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
	 * @throws IOException
	 *             when the output writing fails
	 */
	@SuppressWarnings("rawtypes")
	<T> void serialize(O out, T value, Format format) throws IOException;
}
