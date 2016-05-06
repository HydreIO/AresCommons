package fr.aresrpg.commons.condition;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.aresrpg.commons.Iterators;
import fr.aresrpg.commons.Value;

public interface RawOption<T, O extends RawOption<T, ?>> extends Value<T> {

	@SuppressWarnings("unchecked")
	default O ifPresent(Consumer<? super T> consumer) {
		if (!isEmpty()) consumer.accept(get());
		return (O) this;
	}

	@SuppressWarnings("unchecked")
	default O orRun(Runnable runnable) {
		if (isEmpty()) runnable.run();
		return (O) this;
	}

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
