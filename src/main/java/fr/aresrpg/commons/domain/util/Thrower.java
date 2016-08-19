package fr.aresrpg.commons.domain.util;

import java.util.Objects;

/**
 * An exception wrapper to use for test cases
 * 
 */
@FunctionalInterface
public interface Thrower<T extends Exception> {

	/**
	 * Provide the class of the exception
	 * 
	 * @return the exception class
	 */
	Class<T> value();

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
	default void whenValid(boolean condition) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		if (condition) throw value().newInstance();
	}

	/**
	 * Throw the exception when the condition is false
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
	default void whenUnvalid(boolean condition) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		if (!condition) throw value().newInstance();
	}

	/**
	 * Throw the exception when any of the conditions is true
	 * 
	 * @param conditions
	 *            the array of conditions to throw the exception
	 * @throws InstantiationException
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception to work with
	 */
	default void whenAny(boolean... conditions) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		for (boolean b : conditions)
			whenValid(b);
	}

	/**
	 * Throw the exception when the passed value is null
	 * 
	 * @param value
	 *            the value to test
	 * @throws InstantiationException
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception
	 */
	default <V> void whenNonNull(V value) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		whenValid(Objects.isNull(value));
	}

	/**
	 * Throw the exception when the passed value isn't null
	 * 
	 * @param value
	 *            the value to test
	 * @throws InstantiationException
	 *             if this Class represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             if the class or its nullary constructor is not accessible.
	 * @throws T
	 *             the custom exception
	 */
	default <V> void whenNull(V value) throws InstantiationException, IllegalAccessException, T { // NOSONAR already throwing (reflection) SQUID:S1160
		whenValid(Objects.nonNull(value));
	}

	/**
	 * Provide the util
	 * 
	 * @param exception
	 *            the exception to work with
	 * @return a new Require interface with the exception wrapped
	 */
	public static <T extends Exception> Thrower<T> throwException(Class<T> exception) {
		return () -> exception;
	}
}
