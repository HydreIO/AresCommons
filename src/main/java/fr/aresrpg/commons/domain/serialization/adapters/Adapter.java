package fr.aresrpg.commons.domain.serialization.adapters;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;

/**
 * An converter for an object
 * @param <I> the input type
 * @param <O> the output type
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public interface Adapter<I , O> {
	/**
	 * Convert the value to the output
	 * @param in the input value
	 * @return a representation of the input value
	 */
	O adaptTo(I in);

	/**
	 * Convert back the output into the value
	 * @param out the converted value
	 * @return the value
	 */
	I adaptFrom(O out);

	/**
	 * Get the input type
	 * @return the input type
	 */
	ParametrizedClass<I> getInType();

	/**
	 * Get the output type
	 * @return the output type
	 */
	ParametrizedClass<O> getOutType();
}
