package fr.aresrpg.commons.condition.functional.consumer;

@SuppressWarnings({ "unchecked", "rawtypes" })
@FunctionalInterface
public interface ExecutiveConsumer<T> {

	void accept(T t);

	default <U> ExecutiveConsumer<T> then(ExecutiveConsumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	default <U, A> ExecutiveConsumer<A> thenCommon(Consumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	default <U, A> ExecutiveConsumer<A> thenNative(java.util.function.Consumer<U> other, U u) {
		other.accept(u);
		return none();
	}

	static ExecutiveConsumer none() {
		return Empty.EMPTY_CONSUMER;
	}

	class Empty {
		private static final ExecutiveConsumer EMPTY_CONSUMER = t -> {};
		private Empty(){}
	}

}
