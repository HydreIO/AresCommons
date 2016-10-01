package fr.aresrpg.commons.domain.condition;

import fr.aresrpg.commons.domain.functional.TryExecutable;
import fr.aresrpg.commons.domain.functional.suplier.TrySupplier;
import fr.aresrpg.commons.domain.util.Or;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Try<T> extends RawOption<T, Try<T>> {

	/**
	 * Test the provided executable
	 * 
	 * @param executable
	 *            the executable to test
	 * @return a Try representing the state of the execution
	 */
	static Try<Object> test(TryExecutable executable) {
		try {
			executable.execute();
			return new Ok<>(null);
		} catch (Exception e) {
			return new Error(e);
		}
	}

	/**
	 * Test the provided executable
	 * 
	 * @param supplier
	 *            the supplier to test
	 * @return a Try representing the state of the execution
	 */
	@SuppressWarnings("unchecked")
	static <T> Try<T> test(TrySupplier<T> supplier) {
		try {
			return new Ok<>(supplier.get());
		} catch (Exception e) {
			return (Try<T>) new Error(e);
		}
	}

	/**
	 * Create a try of the provided value
	 * 
	 * @param value
	 *            the return value of this try
	 * @param <T>
	 *            the type of the try
	 * @return a try
	 */
	static <T> Try<T> of(T value) {
		return new Ok<>(value);
	}

	/**
	 * Create a try of the provided throwable
	 * 
	 * @param value
	 *            the throwable of the fail
	 * @param <T>
	 *            the type of the try
	 * @return a try
	 */
	@SuppressWarnings("unchecked")
	static <T> Try<T> of(Throwable value) {
		return (Try<T>) new Error(value);
	}

	@Override
	default <R> Try<R> transform(Function<T, R> function) {
		if (!isEmpty()) return of(function.apply(get()));
		else return of(getError());
	}

	/**
	 * Catch the exception if present
	 * 
	 * @param clazz
	 *            the class of the exception
	 * @param consumer
	 *            the consumer of the exception if present
	 * @param <E>
	 *            the type of the catched throwable
	 * @return this
	 */
	<E extends Throwable> Try<T> catchEx(Class<E> clazz, Consumer<E> consumer);

	/**
	 * Catch the exception
	 * 
	 * @param consumer
	 *            the consumer of the throwable
	 * @return this
	 */
	default Try<T> catchEx(Consumer<Throwable> consumer) {
		return catchEx(Throwable.class, consumer);
	}

	/**
	 * Get the raw of a test
	 * 
	 * @return the value if it was a success
	 * @throws Throwable
	 *             if it was a fail
	 */
	T getRaw() throws Throwable;

	/**
	 * Get the error of this try or null if not present
	 * 
	 * @return a throwable or null
	 */
	Throwable getError();

	/**
	 * Convert the Try to a Or
	 * 
	 * @return a or representing from try
	 */
	Or<T, Throwable> toOr();

	class Ok<T> extends Some<T, Try<T>>implements Try<T> {

		Ok(T value) {
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

		@Override
		public Or<T, Throwable> toOr() {
			return Or.first(get());
		}
	}

	class Error extends None<Try<Object>>implements Try<Object> {
		private final Throwable value;

		Error(Throwable value) {
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

		@Override
		public Or<Object, Throwable> toOr() {
			return Or.second(value);
		}
	}

}
