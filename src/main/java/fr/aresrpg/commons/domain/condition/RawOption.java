package fr.aresrpg.commons.domain.condition;

import fr.aresrpg.commons.domain.Value;
import fr.aresrpg.commons.domain.functional.Executable;
import fr.aresrpg.commons.domain.functional.suplier.Supplier;
import fr.aresrpg.commons.domain.util.Iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A Generic Option
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @param <T>
 *            the type owned by the option
 * @param <O>
 *            the return type to chain
 */
public interface RawOption<T, O extends RawOption<T, ?>> extends Value<T>, Supplier<T> {

	/**
	 * Test if this value is present
	 * 
	 * @return true if the value is present
	 */
	default boolean isPresent() {
		return !isEmpty();
	}

	/**
	 * Execute the consumer if he is present
	 * 
	 * @param consumer
	 *            the consumer to execute
	 * @return the option
	 */
	@SuppressWarnings("unchecked")
	default O ifPresent(Consumer<? super T> consumer) {
		if (isPresent()) consumer.accept(get());
		return (O) this;
	}
	
	/**
	 * Provide the wrapped value if the condition is valid, return the @param value otherwise
	 * </p>
	 * <b>Be careful while using this method and prefer the use of {@linkplain RawOption#whenGet(boolean, Supplier)} when you haven't a primitive value</b>
	 * </p>
	 * 
	 * @param condition
	 *            the condition
	 * @param value
	 *            the value to return if the condition is false
	 * @return the value or the wrapped value
	 */
	default T when(boolean condition, T value) {
		return condition ? value : get();
	}

	/**
	 * Provide the wrapped value if the condition is valid, return the @param value otherwise
	 * 
	 * @param condition
	 *            the condition
	 * @param value
	 *            a supplier of the value to return if the condition is false
	 * @return the value or the wrapped value
	 */
	default T whenGet(boolean condition, Supplier<? extends T> value) {
		return condition ? get() : value.get();
	}

	/**
	 * The passed value is returned if the original wrapped value is null
	 * </p>
	 * <b>Be careful while using this method and prefer the use of {@linkplain RawOption#orElseGet(Supplier)} when you haven't a primitive value</b>
	 * </p>
	 * 
	 * @param other
	 *            the value to return in the case where the wrapped value isn't present
	 * @return the owned value if present or the value provided
	 */
	default T orElse(T other) {
		return when(isEmpty(), other);
	}

	/**
	 * The passed value is returned if the original wrapped value is null
	 * 
	 * @param other
	 *            a supplier of the value to return in the case where the wrapped value isn't present
	 * @return the wrapped value otherwise the supplied value if the value is null
	 */
	default T orElseGet(Supplier<? extends T> other) {
		return whenGet(isEmpty(), other);
	}

	/**
	 * Convert this Option to a Java Optional
	 * 
	 * @return an optional
	 */
	default Optional<T> toOptional() {
		return isPresent() ? Optional.of(get()) : Optional.empty();
	}

	/**
	 * If the value is null then the executable is executed
	 * 
	 * @param executable
	 *            the actions to execute
	 * @return the option
	 */
	@SuppressWarnings("unchecked")
	default O orRun(Executable executable) {
		if (isEmpty()) executable.execute();
		return (O) this;
	}

	/**
	 * Change the type of the wrapped value using a function
	 * </p>
	 * Example of transforming an Integer to a String :
	 * 
	 * <pre>
	 * Option{String} five = Option.some(5).transform(Objects::toString)
	 * </pre>
	 * 
	 * @param function
	 *            the function
	 * @param <R>
	 *            the type of the new option
	 * @return the new option which contain the transformed value
	 */
	@SuppressWarnings("rawtypes")
	<R> RawOption<R, ?> transform(Function<T, R> function);

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
