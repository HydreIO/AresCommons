package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer
 * @param <T> the type of the argument
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface Consumer<T> {
	/**
	 * Run an action
	 * @param a an argument
	 */
	void accept(T a);

	/**
	 * Create an new consumer that execute this consumer an after an other
	 * @param w the consumer to execute after
	 * @return a new consumer
	 */
	default Consumer<T> then(Consumer<T> w) {
		return (T t) -> {
			accept(t);
			w.accept(t);
		};
	}

	/**
	 * Create an empty consumer
	 * @param <T> the type of the consumer
	 * @return an empty consumer
	 */
	static <T> Consumer<T> empty() {
		return t -> {};
	}

	/**
	 * Convert this consumer to a java consumer
	 * @return a java consumer
	 */
	default java.util.function.Consumer<T> toNative() {
		return this::accept;
	}
}
