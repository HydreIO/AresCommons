package fr.aresrpg.commons.condition;

import java.util.function.Function;

public interface Option<T> extends RawOption<T, Option<T>> {
	static <T> Option<T> of(T value) {
		return value == null ? none() : some(value);
	}

	@SuppressWarnings("unchecked")
	static <T> Option<T> none() {
		return (Option<T>) Option.None.INSTANCE;
	}

	static <T> Option<T> empty() {
		return none();
	}

	static <T> Option<T> some(T value) {
		return new Option.Some<>(value);
	}

	@Override
	default <R> Option<R> transform(Function<T, R> function) {
		if (!isEmpty()) return of(function.apply(get()));
		else return none();
	}

	class None extends RawOption.None<Option<Object>> implements Option<Object> {
		private static final Option.None INSTANCE = new Option.None();

		private None() {
		}
	}

	class Some<T> extends RawOption.Some<T, Option<T>> implements Option<T> {
		Some(T value) {
			super(value);
		}
	}
}
