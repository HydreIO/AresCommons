package fr.aresrpg.commons.domain.condition;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.aresrpg.commons.domain.util.Iterators;
import fr.aresrpg.commons.domain.Value;
import fr.aresrpg.commons.domain.functional.Executable;
import fr.aresrpg.commons.domain.functional.suplier.Supplier;

/**
 * A Generic Option
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @param <T> the type owned by the option
 * @param <O> the return type to chain
 */
public interface RawOption<T, O extends RawOption<T, ?>> extends Value<T> , Supplier<T> {

	/**
	 * Test if this value is present
	 * @return true if the value is present
	 */
	default boolean isPresent() {
		return !isEmpty();
	}

	/**
	 * Execute the consumer if is present
	 * @param consumer the consumer to use
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	default O ifPresent(Consumer<? super T> consumer) {
		if (isPresent()) consumer.accept(get());
		return (O) this;
	}

	/**
	 * If condition is true get the owned value or if it's false return the provided value
	 * @param condition the condition
	 * @param value the value to return if the condition is false
	 * @return the value or the owned value
	 */
	default T when(boolean condition, T value) {
		return condition ? get() : value;
	}

	/**
	 * If condition is true get the owned value or if it's false return the provided value
	 * @param condition the condition
	 * @param value the value to get if the condition is false
	 * @return the value or the owned value
	 */
	default T when(boolean condition, Supplier<? extends T> value) {
		return condition ? get() : value.get();
	}

	/**
	 * Return the owned value if present or the value provided
	 * @param other the value to return if is not present
	 * @return the owned value if present or the value provided
	 */
	default T orElse(T other) {
		return when(isEmpty(), other);
	}

	/**
	 * Return the owned value if present or get the value provided
	 * @param other the value to get if is not present
	 * @return the owned value if present or the value provided
	 */
	default T orElse(Supplier<? extends T> other) {
		return when(isEmpty(), other);
	}

	/**
	 * Convert this Option to a Java Optional
	 * @return an optional
	 */
	default Optional<T> toOptional() {
		return isPresent() ? Optional.of(get()) : Optional.empty();
	}

	/**
	 * If the value is present run the provided executable
	 * @param executable the executable to execute
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	default O orRun(Executable executable) {
		if (isEmpty()) executable.execute();
		return (O) this;
	}

	/**
	 * Transform this option using the function provided
	 * @param function function to convert
	 * @param <R> the type of the new option
	 * @return a new option
	 */
	@SuppressWarnings("rawtypes")
	<R> RawOption<R , ?> transform(Function<T, R> function);

	abstract class None<O extends RawOption<Object, ?>> implements RawOption<Object, O> {

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public Object get() {
			return null;
		}

		@Override
		public Iterator<Object> iterator() {
			return Iterators.empty();
		}

	}

	abstract class Some<T, O extends RawOption<T, ?>> implements RawOption<T, O> {
		private final T value;

		public Some(T value) {
			this.value = value;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public T get() {
			return value;
		}

		@Override
		public Iterator<T> iterator() {
			return null;
		}
	}
}
