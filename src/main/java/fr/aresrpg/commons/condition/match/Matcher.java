package fr.aresrpg.commons.condition.match;

import java.util.function.Function;
import java.util.function.Predicate;

import fr.aresrpg.commons.Predicates;

public class Matcher<T, R> {
	private Case<T, R>[] cases;

	private Matcher(Case<T, R>[] cases) {
		this.cases = cases;
	}

	public R match(T value) {
		return match(value, cases);
	}

	@SafeVarargs
	public static <T, R> Matcher<T, R> matcher(Case<T, R>... cases) {
		return new Matcher<>(cases);
	}

	@SafeVarargs
	public static <T, R> R match(T value, Case<T, R>... cases) {
		for (Case<T, R> c : cases) {
			if (c.tester.test(value)) return c.function.apply(value);
		}
		throw new IllegalStateException("No def case");
	}

	public static <T, R> Case<T, R> when(Predicate<T> tester, Function<T, R> function) {
		return new Case<>(tester, function);
	}

	public static <T, R> Case<T, R> when(Predicate<T> tester, R result) {
		return new Case<>(tester, t -> result);
	}

	public static <T, R> Case<T, R> when(Predicate<T> tester, Runnable r) {
		return new Case<>(tester, t -> {
			r.run();
			return null;
		});
	}

	public static <T, R> Case<T, R> def(Function<T, R> function) {
		return when(Predicates.<T> alwaysTrue(), function);
	}

	public static <T, R> Case<T, R> def(R result) {
		return when(Predicates.alwaysTrue(), result);
	}

	public static class Case<T, R> {
		private Predicate<T> tester;
		private Function<T, R> function;

		Case(Predicate<T> tester, Function<T, R> function) {
			this.tester = tester;
			this.function = function;
		}
	}
}
