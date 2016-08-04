package fr.aresrpg.commons.domain.condition;

import java.util.Objects;
import java.util.function.Function;

/**
 * A Option
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @param <T> the type owned by the option
 */
public interface Option<T> extends RawOption<T, Option<T>> {

	/**
	 * Create an option of the value , if null it's none , or is some
	 * @param value the value to use
	 * @param <T> the type of the option
	 * @return the created option from the value
	 */
	static <T> Option<T> of(T value) {
		return value == null ? none() : some(value);
	}

	/**
	 * Get a none option
	 * @param <T> the type of the option
	 * @return the none option instance
	 */
	@SuppressWarnings("unchecked")
	static <T> Option<T> none() {
		return (Option<T>) Option.NoneOption.INSTANCE;
	}

	/**
	 * Create a Some option from the value
	 * @param value the value to use , must be not null
	 * @param <T> the type of the option
	 * @throws NullPointerException  if the value is null
	 * @return a some option
	 */
	static <T> Option<T> some(T value) {
		Objects.requireNonNull(value);
		return new Option.SomeOption<>(value);
	}

	@Override
	default <R> Option<R> transform(Function<T, R> function) {
		if (!isEmpty()) return of(function.apply(get()));
		else return none();
	}

	class NoneOption extends RawOption.None<Option<Object>> implements Option<Object> {
		private static final Option.NoneOption INSTANCE = new Option.NoneOption();

		private NoneOption() {}
	}

	class SomeOption<T> extends RawOption.Some<T, Option<T>> implements Option<T> {
		SomeOption(T value) {
			super(value);
		}
	}
}
