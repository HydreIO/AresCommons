package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer with 6 arguments
 * 
 * @param <A>
 *            the first argument type
 * @param <B>
 *            the second argument type
 * @param <C>
 *            the third argument type
 * @param <D>
 *            the fourth argument type
 * @param <E>
 *            the fifth argument type
 * @param <F>
 *            the sixth argument type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@FunctionalInterface
public interface HexaConsumer<A, B, C, D, E, F> {
	/**
	 * Execute an action
	 * 
	 * @param a
	 *            the first argument
	 * @param b
	 *            the second argument
	 * @param c
	 *            the third argument
	 * @param d
	 *            the fourth argument
	 * @param e
	 *            the fifth argument
	 * @param f
	 *            the sixth argument
	 */
	void accept(A a, B b, C c, D d, E e, F f);

	/**
	 * Create an new consumer which will be executed after the current one
	 * 
	 * @param after
	 *            the consumer to execute after
	 * @return a new consumer
	 */
	default HexaConsumer<A, B, C, D, E, F> then(HexaConsumer<A, B, C, D, E, F> after) {
		return (a, b, c, d, e, f) -> {
			accept(a, b, c, d, e, f);
			after.accept(a, b, c, d, e, f);
		};
	}
}
