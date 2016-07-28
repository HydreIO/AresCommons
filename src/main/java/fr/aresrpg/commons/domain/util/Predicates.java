package fr.aresrpg.commons.domain.util;

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * An util class to use predicates
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class Predicates {
	private static final Predicate<?> TRUE = o -> true;
	private static final Predicate<?> FALSE = o -> false;
	private static final Predicate<?> NULL = Objects::isNull;
	private static final Predicate<?> NON_NULL = Objects::nonNull;

	/**
	 * This constructor can be called
	 * @throws IllegalConstructionException always
	 */
	private Predicates() { throw new IllegalConstructionException();}

	/**
	 * Get a predicate to test if an integer is in range
	 * @param min the min value of this range
	 * @param max the max value of this range
	 * @return a predicate to test if the int is in this range
	 */
	public static IntPredicate range(int min, int max) {
		return i -> i > min && i < max;
	}

	/**
	 * Get a predicate witch always return true
	 * @param <T> the type of the predicate
	 * @return a predicate always true
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> alwaysTrue() {
		return (Predicate<T>) TRUE;
	}

	/**
	 * Get a predicate witch always return false
	 * @param <T> the type of the predicate
	 * @return a predicate always false
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> alwaysFalse() {
		return (Predicate<T>) FALSE;
	}

	/**
	 * Get a predicate to test if is an instance of the class
	 * @param clazz the class to test if is an instance of
	 * @param <T> the type of the predicate
	 * @return a predicate always true
	 */
	public static <T> Predicate<T> instanceOf(Class<?> clazz) {
		return clazz::isInstance;
	}

	/**
	 * Get a predicate to test if is equal
	 * @param value the value to test if is equal
	 * @param <T> the type of the predicate
	 * @return a predicate to test if an object is equals
	 */
	public static <T> Predicate<T> is(T value) {
		return o -> Objects.equals(o, value);
	}

	/**
	 * Get a predicate to test if is an not equal
	 * @param value the value to test if is not equal
	 * @param <T> the type of the predicate
	 * @return a predicate to test if an object is not equals
	 */
	public static <T> Predicate<T> isNot(T value) {
		return o -> !Objects.equals(o, value);
	}

	/**
	 * Get a predicate to test if the value is in values
	 * @param values the values with the value to test must be in
	 * @param <T> the type of the predicate
	 * @return a predicate to test if the value is in values
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
	 * Get a predicate to test if the value is null
	 * @param <T> the type of the predicate
	 * @return a predicate to test if the value is null
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> isNull() {
		return (Predicate<T>) NULL;
	}

	/**
	 * Get a predicate to test if the value is not null
	 * @param <T> the type of the predicate
	 * @return a predicate to test if the value is not null
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> isNotNull() {
		return (Predicate<T>) NON_NULL;
	}

	/**
	 * Get a predicate to test if the value can be tested by all predicates
	 * @param predicates the predicates witch must be tested
	 * @param <T> the type of the predicate
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
	 * Get a predicate to test if the value can be tested by one of the predicates
	 * @param predicates the predicates witch must be tested
	 * @param <T> the type of the predicate
	 * @return a predicate to test if the value can be tested by one of the predicates
	 */
	@SafeVarargs
	public static <T> Predicate<T> any(Predicate<T>... predicates) {
		return o -> {
			for (Predicate<T> p : predicates)
				if (p.test(o)) return true;
			return false;
		};
	}

	/**
	 * Get a predicate to test if the value can be tested by no one predicate
	 * @param predicates the predicates witch must be tested
	 * @param <T> the type of the predicate
	 * @return a predicate to test if the value can be tested by no one predicate
	 */
	@SafeVarargs
	public static <T> Predicate<T> none(Predicate<T>... predicates) {
		return all(predicates).negate();
	}
}
