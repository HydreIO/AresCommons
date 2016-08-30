package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer with 7 arguments
 * @param <A> the first argument type
 * @param <B> the second argument type
 * @param <C> the third argument type
 * @param <D> the fourth argument type
 * @param <E> the fifth argument type
 * @param <F> the sixth argument type
 * @param <G> the seventh argument type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface HeptaConsumer<A, B, C, D, E, F, G> {
	/**
	 * Execute an action
	 * @param a the first argument
	 * @param b the second argument
	 * @param c the third argument
	 * @param d the fourth argument
	 * @param e the fifth argument
	 * @param f the sixth argument
	 * @param g the seventh argument
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g);

	/**
	 * Create an new consumer that execute this consumer an after an other
	 * @param after the consumer to execute after
	 * @return a new consumer
	 */
	default HeptaConsumer<A, B, C, D, E, F, G> then(HeptaConsumer<A, B, C, D, E, F, G> after) {
		return (a, b, c, d, e, f, g) -> {
			accept(a, b, c, d, e, f, g);
			after.accept(a, b, c, d, e, f, g);
		};
	}
}
