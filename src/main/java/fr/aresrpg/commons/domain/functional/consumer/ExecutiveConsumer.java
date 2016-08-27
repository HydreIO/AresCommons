package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A ExecutiveConsumer is a consumer that can instantly consume value in chaining operations
 * 
 * @Since 0.6
 * @param <T>
 *            the type of the consumed value
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@FunctionalInterface
public interface ExecutiveConsumer<T> {

	/**
	 * Execute the consumer with the given value
	 * 
	 * @param t
	 *            The value
	 */
	void accept(T t);

	/**
	 * Execute the passed consumer with the passed value !<br>
	 * Used in chaining actions
	 * 
	 * @param other
	 *            The consummer wich will be executed instantly
	 * @param u
	 *            the value for the consumer
	 * @return an empty consumer to continue chaining
	 */
	default <U> ExecutiveConsumer<T> then(ExecutiveConsumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	/**
	 * Execute the passed consumer with the passed value !<br>
	 * Used in chaining actions
	 * <p>
	 * The difference between {@linkplain ExecutiveConsumer#then(ExecutiveConsumer, Object)} and this method is that here it take a {@linkplain Consumer} instead of a {@linkplain ExecutiveConsumer}
	 * </p>
	 * 
	 * @param other
	 *            The consummer wich will be executed instantly
	 * @param u
	 *            the value for the consumer
	 * @return an empty consumer to continue chaining
	 */
	default <U, A> ExecutiveConsumer<A> thenCommon(Consumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	/**
	 * Execute the passed consumer with the passed value !<br>
	 * Used in chaining actions
	 * <p>
	 * The difference between {@linkplain ExecutiveConsumer#thenCommon(Consumer, Object)} and this method is that here it take a {@linkplain java.util.function.Consumer} instead of a {@linkplain Consumer}
	 * </p>
	 * 
	 * @param other
	 *            The consummer wich will be executed instantly
	 * @param u
	 *            the value for the consumer
	 * @return an empty consumer to continue chaining
	 */
	default <U, A> ExecutiveConsumer<A> thenNative(java.util.function.Consumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	/**
	 * Get an empty {@linkplain ExecutiveConsumer}
	 * 
	 * @return an empty {@linkplain ExecutiveConsumer}
	 */
	static ExecutiveConsumer none() {
		return Empty.EMPTY_CONSUMER;
	}

	class Empty {
		private static final ExecutiveConsumer EMPTY_CONSUMER = t -> {};

		private Empty() {
		}
	}

}
