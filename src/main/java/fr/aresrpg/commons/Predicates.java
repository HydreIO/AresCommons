package fr.aresrpg.commons;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {
	private static final Predicate<?> TRUE = o -> true;

	private Predicates() {
	}

	public static Predicate<Integer> range(int min, int max) {
		return i -> i > min && i < max;
	}

	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> alwaysTrue() {
		return (Predicate<T>) TRUE;
	}

	public static <T> Predicate<T> instanceOf(Class<?> clazz) {
		return clazz::isInstance;
	}

	public static <T> Predicate<T> is(T value) {
		return o -> Objects.equals(o, value);
	}

	@SafeVarargs
	public static <T> Predicate<T> in(T... values) {
		return o -> {
			for (T value : values)
				if (Objects.equals(o, value)) return true;
			return false;
		};
	}

	public static <T> Predicate<T> isNull() {
		return Objects::isNull;
	}

	public static <T> Predicate<T> isNotNull() {
		return Objects::nonNull;
	}

	@SafeVarargs
	public static <T> Predicate<T> all(Predicate<T>... predicate) {
		return o -> {
			for (Predicate<T> p : predicate)
				if (!p.test(o)) return false;
			return true;
		};
	}

	@SafeVarargs
	public static <T> Predicate<T> any(Predicate<T>... predicate) {
		return o -> {
			for (Predicate<T> p : predicate)
				if (p.test(o)) return true;
			return false;
		};
	}

	@SafeVarargs
	public static <T> Predicate<T> none(Predicate<T>... predicate) {
		return all(predicate).negate();
	}
}
