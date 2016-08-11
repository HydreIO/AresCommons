package fr.aresrpg.commons.domain.util;

import java.util.Objects;

/**
 * An exception wrapper to use for test cases
 * 
 * @Since 0.0.1
 */
@FunctionalInterface
public interface Require<T extends Exception> {

	/**
	 * Provide the class of the exception
	 * 
	 * @return the exception class
	 */
	Class<T> exc();

	/**
	 * Throw the exception when the condition is true
	 * 
	 * @param condition
	 *            the condition to throw the exception
	 * @throws InstantiationException
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception to work with
	 */
	default void when(boolean condition) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		if (condition) throw exc().newInstance();
	}

	/**
	 * Throw the exception when any of the conditions is true
	 * 
	 * @param condition
	 *            the array of conditions to throw the exception
	 * @throws InstantiationException
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception to work with
	 */
	default void any(boolean... conditions) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		for (boolean b : conditions)
			when(b);
	}

	/**
	 * Throw the exception when the passed value is null
	 * 
	 * @param value
	 *            the value to test
	 * @throws InstantiationExceptin
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception
	 */
	default <V> void nonNull(V value) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		when(Objects.isNull(value));
	}

	/**
	 * Throw the exception when the passed value isn't null
	 * 
	 * @param value
	 *            the value to test
	 * @throws InstantiationExceptin
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception
	 */
	default <V> void nullValue(V value) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		when(Objects.nonNull(value));
	}

	/**
	 * Provide the util
	 * 
	 * @param exception
	 *            the exception to work with
	 * @return a new Require interface with the exception wrapped
	 */
	public static <T extends Exception> Require<T> from(Class<T> exception) {
		return () -> exception;
	}

}