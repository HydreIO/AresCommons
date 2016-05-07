package fr.aresrpg.commons.condition;

import java.util.function.Consumer;
import java.util.function.Function;

import fr.aresrpg.commons.condition.functional.TrySupplier;
import fr.aresrpg.commons.condition.functional.TryRunnable;

public interface Try<T> extends RawOption<T, Try<T>> {

	static Try<Object> test(TryRunnable runnable) {
		try {
			runnable.run();
			return new Ok<>(null);
		} catch (Throwable t) {
			return new Error(t);
		}
	}

	@SuppressWarnings("unchecked")
	static <T> Try<T> test(TrySupplier<T> callable) {
		try {
			return new Ok<>(callable.call());
		} catch (Throwable t) {
			return (Try<T>) new Error(t);
		}
	}

	static <T> Try<T> of(T value) {
		return new Ok<>(value);
	}

	@SuppressWarnings("unchecked")
	static <T> Try<T> of(Throwable value) {
		return (Try<T>) new Error(value);
	}

	@Override
	default <R> Try<R> transform(Function<T, R> function) {
		if (!isEmpty()) return of(function.apply(get()));
		else return of(getError());
	}

	<E extends Throwable> Try<T> catchEx(Class<E> clazz, Consumer<E> consumer);

	default Try<T> catchEx(Consumer<Throwable> consumer) {
		return catchEx(Throwable.class, consumer);
	}

	T getRaw() throws Throwable;

	Throwable getError();

	class Ok<T> extends Some<T, Try<T>> implements Try<T> {

		public Ok(T value) {
			super(value);
		}

		@Override
		public <E extends Throwable> Try<T> catchEx(Class<E> clazz, Consumer<E> consumer) {
			return this;
		}

		@Override
		public T getRaw() throws Throwable {
			return get();
		}

		@Override
		public Throwable getError() {
			return null;
		}
	}

	class Error extends None<Try<Object>> implements Try<Object> {
		private final Throwable value;

		public Error(Throwable value) {
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public <E extends Throwable> Try<Object> catchEx(Class<E> clazz, Consumer<E> consumer) {
			if (clazz.isInstance(value)) consumer.accept((E) value);
			return this;
		}

		@Override
		public Object getRaw() throws Throwable {
			throw value;
		}

		@Override
		public Throwable getError() {
			return value;
		}
	}

}
