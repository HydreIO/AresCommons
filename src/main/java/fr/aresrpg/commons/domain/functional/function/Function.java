package fr.aresrpg.commons.domain.functional.function;

/**
 * A function
 * 
 * @param <A>
 *            the argument type
 * @param <R>
 *            the result type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface Function<A, R> {
	/**
	 * Execute this function
	 * 
	 * @param a
	 *            an argument
	 * @return a result
	 */
	R apply(A a);

	/**
	 * Apply a function before the current
	 * 
	 * @param before
	 *            the function to apply before
	 * @param <V>
	 *            the type of the function input
	 * @return a new function
	 */
	default <V> Function<V, R> applyBefore(Function<V, A> before) {
		return v -> apply(before.apply(v));
	}

	/**
	 * Apply a function after the current
	 * 
	 * @param after
	 *            the function to apply after
	 * @param <V>
	 *            the type of the function output
	 * @return a new function
	 */
	default <V> Function<A, V> applyAfter(Function<R, V> after) {
		return a -> after.apply(apply(a));
	}

	/**
	 * Return an identity function
	 * 
	 * @param <T>
	 *            the type of the function
	 * @return an identity function
	 */
	static <T> Function<T, T> identity() {
		return t -> t;
	}
}
