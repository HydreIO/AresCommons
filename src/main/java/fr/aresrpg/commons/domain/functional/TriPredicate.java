package fr.aresrpg.commons.domain.functional;

/**
 * A tri predicate class
 * 
 * @since 1.7
 */
@FunctionalInterface
public interface TriPredicate<F, S, T> {

	/**
	 * Test 3 values
	 * 
	 * @param f
	 *            the first value
	 * @param s
	 *            the second value
	 * @param t
	 *            the third value
	 * @return true if the predicate is valid, false otherwise
	 */
	boolean test(F f, S s, T t);

	default void ifValid(F f, S s, T t, Runnable action) {
		if (test(f, s, t)) action.run();
	}

}
