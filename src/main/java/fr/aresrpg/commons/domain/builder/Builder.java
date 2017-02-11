package fr.aresrpg.commons.domain.builder;

/**
 * Used to specify this class is a builder
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @param <T>
 *            the builded type
 */
@FunctionalInterface
public interface Builder<T> {
	/**
	 * Build the object
	 * 
	 * @return a builded object
	 */
	T build();



}
