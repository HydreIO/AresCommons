package fr.aresrpg.commons.domain.condition;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.aresrpg.commons.domain.util.Iterators;
import fr.aresrpg.commons.domain.Value;
import fr.aresrpg.commons.domain.functional.Executable;
import fr.aresrpg.commons.domain.functional.suplier.Supplier;

public interface RawOption<T, O extends RawOption<T, ?>> extends Value<T> , Supplier<T> {

	default boolean isPresent() {
		return !isEmpty();
	}

	@SuppressWarnings("unchecked")
	default O ifPresent(Consumer<? super T> consumer) {
		if (isPresent()) consumer.accept(get());
		return (O) this;
	}

	default T when(boolean condition, T value) {
		return condition ? get() : value;
	}

	default T when(boolean condition, Supplier<? extends T> value) {
		return condition ? get() : value.get();
	}

	default T orElse(T other) {
		return when(isEmpty(), other);
	}

	default T orElse(Supplier<? extends T> other) {
		return when(isEmpty(), other);
	}

	default Optional<T> toOptional() {
		return isPresent() ? Optional.of(get()) : Optional.empty();
	}

	@SuppressWarnings("unchecked")
	default O orRun(Executable executable) {
		if (isEmpty()) executable.execute();
		return (O) this;
	}

	@SuppressWarnings("rawtypes")
	<R> RawOption transform(Function<T, R> function);

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
