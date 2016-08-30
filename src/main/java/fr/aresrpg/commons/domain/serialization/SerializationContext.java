package fr.aresrpg.commons.domain.serialization;

import java.io.IOException;

import fr.aresrpg.commons.domain.serialization.formats.Format;

/**
 * A serialization context
 * @param <I> the input type
 * @param <O> the output type
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public interface SerializationContext<I , O> {
	/**
	 * Serialize this value using the provided format
	 * @param out the output
	 * @param value the value to serialize
	 * @param format the format to use
	 * @param <T> the type of the value
	 * @throws IOException if the write to the output fal
	 */
	@SuppressWarnings("rawtypes")
	<T> void serialize(O out, T value, Format format) throws IOException;
}
