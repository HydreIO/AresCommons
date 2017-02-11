package fr.aresrpg.commons.domain.util;

import fr.aresrpg.commons.domain.util.exception.IllegalConstructionException;

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * An util class to use predicates
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Predicates {
	private static final Predicate<?> TRUE = o -> true;
	private static final Predicate<?> FALSE = o -> false;
	private static final Predicate<?> NULL = Objects::isNull;
	private static final Predicate<?> NON_NULL = Objects::nonNull;

	/**
	 * This constructor can't be called
	 * 
	 * @throws IllegalConstructionException
	 *             always
	 */
	private Predicates() {
		throw new IllegalConstructionException();
	}

	/**
	 * Test if an integer is in range
	 * 
	 * @param min
	 *            the min value of this range
	 * @param max
	 *            the max value of this range
	 * @return a predicate to test if the int is in the given range
	 */
	public static IntPredicate range(int min, int max) {
		return i -> i > min && i < max;
	}

	/**
	 * Get a predicate which always return true
	 * 
	 * @param <T>
	 *            the type of the tested object
	 * @return a predicate always true
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> alwaysTrue() {
		return (Predicate<T>) TRUE;
	}

	/**
	 * Get a predicate which always return false
	 * 
	 * @param <T>
	 *            the type of the tested object
	 * @return a predicate always false
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> alwaysFalse() {
		return (Predicate<T>) FALSE;
	}

	/**
	 * Test if an object is an instance of the given class
	 * 
	 * @param clazz
	 *            the class that represent the tested instance
	 * @param <T>
	 *            the type of the tested object
	 * @return a predicate to test if an object is an instance of the class
	 */
	public static <T> Predicate<T> instanceOf(Class<?> clazz) {
		return clazz::isInstance;
	}

	/**
	 * Test the equality with a given value
	 * 
	 * @param value
	 *            the value to test if there is an equality
	 * @param <T>
	 *            the type of the tested object
	 * @return a predicate to test if an object is equal to the given value
	 */
	public static <T> Predicate<T> is(T value) {
		return o -> Objects.equals(o, value);
	}

	/**
	 * Test the equality with a given value
	 * 
	 * @param value
	 *            the value to test if there is'nt an equality
	 * @param <T>
	 *            the type of the tested object
	 * @return a predicate to test if an object is not equal to the given value
	 */
	public static <T> Predicate<T> isNot(T value) {
		return is(value).negate();
	}

	/**
	 * Test if a value is equal to any of the given values
	 * 
	 * @param values
	 *            an array of the values to test if they contains the tested value
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if the value is equal to any of the values
	 */
	@SafeVarargs
	public static <T> Predicate<T> in(T... values) {
		return o -> {
			for (T value : values)
				if (Objects.equals(o, value)) return true;
			return false;
		};
	}

	/**
	 * Test if a value is null
	 * 
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if the value is null
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> isNull() {
		return (Predicate<T>) NULL;
	}

	/**
	 * Test if a value is'nt null
	 * 
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if the value is'nt null
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> isNotNull() {
		return (Predicate<T>) NON_NULL;
	}

	/**
	 * Test if all passed predicates are valid
	 * 
	 * @param predicates
	 *            the predicates which must be tested
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if the value can be tested by all predicates
	 */
	@SafeVarargs
	public static <T> Predicate<T> all(Predicate<T>... predicates) {
		return o -> {
			for (Predicate<T> p : predicates)
				if (!p.test(o)) return false;
			return true;
		};
	}

	/**
	 * Test if one of the passed predicates is true
	 * 
	 * @param predicates
	 *            the predicates which must be tested
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if one of the passed predicates is valid
	 */
	@SafeVarargs
	public static <T> Predicate<T> any(Predicate<T>... predicates) {
		return none(predicates).negate();
	}

	/**
	 * Test if one of the passed predicates is false
	 * 
	 * @param predicates
	 *            the predicates which must be tested
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if any of the passed predicates is false
	 */
	public static <T> Predicate<T> anyFalse(Predicate<T>... predicates) {
		return all(predicates).negate();
	}

	/**
	 * Test if all the passed predicates are false
	 * 
	 * @param predicates
	 *            the predicates witch must be tested
	 * @param <T>
	 *            the type of the tested value
	 * @return a predicate to test if the value can be tested by no one predicate
	 */
	@SafeVarargs
	public static <T> Predicate<T> none(Predicate<T>... predicates) {
		return o -> {
			for (Predicate<T> p : predicates)
				if (p.test(o)) return false;
			return true;
		};
	}
}
