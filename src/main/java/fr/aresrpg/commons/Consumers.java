package fr.aresrpg.commons;

import java.util.function.Predicate;

import fr.aresrpg.commons.condition.functional.Executable;
import fr.aresrpg.commons.condition.functional.consumer.BiConsumer;
import fr.aresrpg.commons.condition.functional.consumer.Consumer;
import fr.aresrpg.commons.condition.functional.consumer.ExecutiveConsumer;
import fr.aresrpg.commons.condition.functional.consumer.HeptaConsumer;
import fr.aresrpg.commons.condition.functional.consumer.HexaConsumer;
import fr.aresrpg.commons.condition.functional.consumer.PentaConsumer;
import fr.aresrpg.commons.condition.functional.consumer.TetraConsumer;
import fr.aresrpg.commons.condition.functional.consumer.TriConsumer;

@SuppressWarnings("unchecked")
public class Consumers {
	private Consumers() {
	}

	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T, R> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, Predicate<R> pr, R r) {
		if (pr.test(r)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, Predicate<T> p) {
		if (p.test(t)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t) {
		return executeNative(consumer, t, true);
	}

	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T, R> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, Predicate<R> pr, R r) {
		if (pr.test(r)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, Predicate<T> pr) {
		if (pr.test(t)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> e, T t) {
		return executeCommon(e, t, true);
	}

	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T, R> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, Predicate<R> pr, R r) {
		if (pr.test(r)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, Predicate<T> pr) {
		if (pr.test(t)) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t) {
		return execute(consumer, t, true);
	}

	public static <T> ExecutiveConsumer<T> execute(Executable executable, boolean condition) {
		if (condition) executable.execute();
		return ExecutiveConsumer.none();
	}

	public static <T, R> ExecutiveConsumer<T> execute(Executable executable, Predicate<R> pr, R r) {
		if (pr.test(r)) executable.execute();
		return ExecutiveConsumer.none();
	}

	public static <T> ExecutiveConsumer<T> execute(Executable executable) {
		return execute(executable, true);
	}

	public static <A, B> Consumer<B> from(BiConsumer<A, B> f, A a) {
		return b -> f.accept(a, b);
	}

	public static <A, B, C> Consumer<C> from(TriConsumer<A, B, C> f, A a, B b) {
		return c -> f.accept(a, b, c);
	}

	public static <A, B, C, D> Consumer<D> from(TetraConsumer<A, B, C, D> f, A a, B b, C c) {
		return d -> f.accept(a, b, c, d);
	}

	public static <A, B, C, D, E> Consumer<E> from(PentaConsumer<A, B, C, D, E> f, A a, B b, C c, D d) {
		return e -> f.accept(a, b, c, d, e);
	}

	public static <A, B, C, D, E, F> Consumer<F> from(HexaConsumer<A, B, C, D, E, F> t, A a, B b, C c, D d, E e) {
		return f -> t.accept(a, b, c, d, e, f);
	}

	public static <A, B, C, D, E, F, G> Consumer<G> from(HeptaConsumer<A, B, C, D, E, F, G> t, A a, B b, C c, D d, E e, F f) {
		return g -> t.accept(a, b, c, d, e, f, g);
	}
}
