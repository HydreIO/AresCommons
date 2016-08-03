package fr.aresrpg.commons.domain.util;

import java.util.function.Predicate;

import fr.aresrpg.commons.domain.functional.Executable;
import fr.aresrpg.commons.domain.functional.consumer.BiConsumer;
import fr.aresrpg.commons.domain.functional.consumer.Consumer;
import fr.aresrpg.commons.domain.functional.consumer.ExecutiveConsumer;
import fr.aresrpg.commons.domain.functional.consumer.HeptaConsumer;
import fr.aresrpg.commons.domain.functional.consumer.HexaConsumer;
import fr.aresrpg.commons.domain.functional.consumer.PentaConsumer;
import fr.aresrpg.commons.domain.functional.consumer.TetraConsumer;
import fr.aresrpg.commons.domain.functional.consumer.TriConsumer;

/**
 * A util class to use with Consumers
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@SuppressWarnings("unchecked")
public class Consumers {
	private Consumers() {}

	/**
	 * Execute the java consumer with value only if boolean is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param condition if true execute consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	/**
	 * Execute the java consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param pr the predicate to test to determine if the consumer must be executed
	 * @param r the value to pass to the predicate
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T, R> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, Predicate<R> pr, R r) {
		return executeNative(consumer , t , pr.test(r));
	}

	/**
	 * Execute the java consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer and predicate
	 * @param p the predicate to test to determine if the consumer must be executed
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t, Predicate<T> p) {
		return executeNative(consumer , t , p , t);
	}

	/**
	 * Execute the java consumer with value
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer and predicate
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeNative(java.util.function.Consumer<T> consumer, T t) {
		return executeNative(consumer, t, true);
	}

	/**
	 * Execute the ares consumer with value only if boolean is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param condition if true execute consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	/**
	 * Execute the ares consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param pr the predicate to test to determine if the consumer must be executed
	 * @param r the value to pass to the predicate
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T, R> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, Predicate<R> pr, R r) {
		return executeCommon(consumer , t , pr.test(r));
	}

	/**
	 * Execute the ares consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer and predicate
	 * @param p the predicate to test to determine if the consumer must be executed
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t, Predicate<T> p) {
		return executeCommon(consumer, t, p ,t);
	}

	/**
	 * Execute the ares consumer with value
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> executeCommon(Consumer<T> consumer, T t) {
		return executeCommon(consumer, t, true);
	}

	/**
	 * Execute the executive consumer with value only if boolean is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param condition if true execute consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, boolean condition) {
		if (condition) consumer.accept(t);
		return ExecutiveConsumer.none();
	}

	/**
	 * Execute the executive consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t the value to pass to consumer
	 * @param pr the predicate to test to determine if the consumer must be executed
	 * @param r the value to pass to the predicate
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T, R> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, Predicate<R> pr, R r) {
		return execute(consumer, t, pr.test(r));
	}

	/**
	 * Execute the executive consumer with value only if predicate is true
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer and predicate
	 * @param p the predicate to test to determine if the consumer must be executed
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t, Predicate<T> p) {
		return execute(consumer, t, p, t);
	}

	/**
	 * Execute the executive consumer with value
	 * @param consumer the consumer to execute
	 * @param t he value to pass to consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> execute(ExecutiveConsumer<T> consumer, T t) {
		return execute(consumer, t, true);
	}

	/**
	 * Execute the executable only if boolean is true
	 * @param executable the executable to execute
	 * @param condition if true execute consumer
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> execute(Executable executable, boolean condition) {
		if (condition) executable.execute();
		return ExecutiveConsumer.none();
	}
	/**
	 * Execute the executable only if predicate is true
	 * @param executable the executable to execute
	 * @param pr the predicate to test to determine if the consumer must be executed
	 * @param r the value to pass to the predicate
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T, R> ExecutiveConsumer<T> execute(Executable executable, Predicate<R> pr, R r) {
		return execute(executable , pr.test(r));
	}

	/**
	 * Execute the executable
	 * @param executable the executable to execute
	 * @param <T> the type of the consumer
	 * @return an ExecutiveConsumer to chain
	 */
	public static <T> ExecutiveConsumer<T> execute(Executable executable) {
		return execute(executable, true);
	}

	/**
	 * Create a consumer from a BiConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @return a Consumer of B
	 * @see #from(TriConsumer, Object, Object)
	 * @see #from(TetraConsumer, Object, Object, Object)
	 * @see #from(PentaConsumer, Object, Object, Object, Object)
	 * @see #from(HexaConsumer, Object, Object, Object, Object, Object)
	 * @see #from(HeptaConsumer, Object, Object, Object, Object, Object, Object)
	 */
	public static <A, B> Consumer<B> from(BiConsumer<A, B> consumer, A a) {
		return b -> consumer.accept(a, b);
	}

	/**
	 * Create a consumer from a TriConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param b the value to always pass to b
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @param <C> the type of C
	 * @return a Consumer of C
	 * @see #from(BiConsumer, Object)
	 * @see #from(TetraConsumer, Object, Object, Object)
	 * @see #from(PentaConsumer, Object, Object, Object, Object)
	 * @see #from(HexaConsumer, Object, Object, Object, Object, Object)
	 * @see #from(HeptaConsumer, Object, Object, Object, Object, Object, Object)
	 */
	public static <A, B, C> Consumer<C> from(TriConsumer<A, B, C> consumer, A a, B b) {
		return c -> consumer.accept(a, b, c);
	}

	/**
	 * Create a consumer from a TetraConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param b the value to always pass to b
	 * @param c the value to always pass to c
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @param <C> the type of C
	 * @param <D> the type of D
	 * @return a Consumer of D
	 * @see #from(BiConsumer, Object)
	 * @see #from(TriConsumer, Object, Object)
	 * @see #from(PentaConsumer, Object, Object, Object, Object)
	 * @see #from(HexaConsumer, Object, Object, Object, Object, Object)
	 * @see #from(HeptaConsumer, Object, Object, Object, Object, Object, Object)
	 */
	public static <A, B, C, D> Consumer<D> from(TetraConsumer<A, B, C, D> consumer, A a, B b, C c) {
		return d -> consumer.accept(a, b, c, d);
	}

	/**
	 * Create a consumer from a PentaConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param b the value to always pass to b
	 * @param c the value to always pass to c
	 * @param d the value to always pass to d
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @param <C> the type of C
	 * @param <D> the type of D
	 * @param <E> the type of E
	 * @return a Consumer of E
	 * @see #from(BiConsumer, Object)
	 * @see #from(TriConsumer, Object, Object)
	 * @see #from(TetraConsumer, Object, Object, Object)
	 * @see #from(HexaConsumer, Object, Object, Object, Object, Object)
	 * @see #from(HeptaConsumer, Object, Object, Object, Object, Object, Object)
	 */
	public static <A, B, C, D, E> Consumer<E> from(PentaConsumer<A, B, C, D, E> consumer, A a, B b, C c, D d) {
		return e -> consumer.accept(a, b, c, d, e);
	}

	/**
	 * Create a consumer from a HexaConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param b the value to always pass to b
	 * @param c the value to always pass to c
	 * @param d the value to always pass to d
	 * @param e the value to always pass to e
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @param <C> the type of C
	 * @param <D> the type of D
	 * @param <E> the type of E
	 * @param <F> the type of F
	 * @return a Consumer of F
	 * @see #from(BiConsumer, Object)
	 * @see #from(TriConsumer, Object, Object)
	 * @see #from(TetraConsumer, Object, Object, Object)
	 * @see #from(PentaConsumer, Object, Object, Object, Object)
	 * @see #from(HeptaConsumer, Object, Object, Object, Object, Object, Object)
	 */
	public static <A, B, C, D, E, F> Consumer<F> from(HexaConsumer<A, B, C, D, E, F> consumer, A a, B b, C c, D d, E e) {
		return f -> consumer.accept(a, b, c, d, e, f);
	}

	/**
	 * Create a consumer from a PentaConsumer
	 * @param consumer the consumer to transform
	 * @param a the value to always pass to a
	 * @param b the value to always pass to b
	 * @param c the value to always pass to c
	 * @param d the value to always pass to d
	 * @param e the value to always pass to e
	 * @param f the value to always pass to e
	 * @param <A> the type of A
	 * @param <B> the type of B
	 * @param <C> the type of C
	 * @param <D> the type of D
	 * @param <E> the type of E
	 * @param <F> the type of F
	 * @param <G> the type of F
	 * @return a Consumer of G
	 * @see #from(BiConsumer, Object)
	 * @see #from(TriConsumer, Object, Object)
	 * @see #from(TetraConsumer, Object, Object, Object)
	 * @see #from(PentaConsumer, Object, Object, Object, Object)
	 * @see #from(HexaConsumer, Object, Object, Object, Object, Object)
	 */
	public static <A, B, C, D, E, F, G> Consumer<G> from(HeptaConsumer<A, B, C, D, E, F, G> consumer, A a, B b, C c, D d, E e, F f) {
		return g -> consumer.accept(a, b, c, d, e, f, g);
	}
}
