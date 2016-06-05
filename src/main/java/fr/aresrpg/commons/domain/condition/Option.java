package fr.aresrpg.commons.domain.condition;

import java.util.function.Function;

public interface Option<T> extends RawOption<T, Option<T>> {
	static <T> Option<T> of(T value) {
		return value == null ? none() : some(value);
	}

	@SuppressWarnings("unchecked")
	static <T> Option<T> none() {
		return (Option<T>) Option.NoneOption.INSTANCE;
	}

	static <T> Option<T> some(T value) {
		return new Option.SomeOption<>(value);
	}

	@Override
	default <R> Option<R> transform(Function<T, R> function) {
		if (!isEmpty()) return of(function.apply(get()));
		else return none();
	}

	class NoneOption extends RawOption.None<Option<Object>> implements Option<Object> {
		private static final Option.NoneOption INSTANCE = new Option.NoneOption();

		private NoneOption() {
		}
	}

	class SomeOption<T> extends RawOption.Some<T, Option<T>> implements Option<T> {
		SomeOption(T value) {
			super(value);
		}
	}
}
