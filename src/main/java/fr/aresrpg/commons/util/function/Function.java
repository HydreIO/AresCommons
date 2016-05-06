package fr.aresrpg.commons.util.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import fr.aresrpg.commons.condition.Option;

/**
 * Cosmos entrance
 * 
 * @author Sylvain pierre durif
 *
 */
@FunctionalInterface
public interface Function<λ> {

	/**
	 * same as consumer.accept(λ);
	 * 
	 * @param λ
	 */
	void aldebaran(λ λ);

	/**
	 * same as consumer.andThen(Consumer<λ> λ);
	 * 
	 * @param t
	 * @return
	 */
	default Function<λ> then(Function<λ> t) {
		return (λ λ) -> {
			aldebaran(λ);
			t.aldebaran(λ);
		};
	}

	/**
	 * Execute t with δ
	 * 
	 * @param t
	 *            function to exec
	 * @param δ
	 *            args to consume
	 * @return empty for chain
	 */
	default <δ> Function<λ> another(Function<δ> t, δ δ) {
		t.aldebaran(δ);
		return empty();
	}

	/**
	 * Same as {@link Function#another(Function, Object)} but with a consumer
	 * 
	 * @param t
	 *            consumer
	 * @param δ
	 *            arg
	 * @return empty for chain
	 */
	default <δ> Function<λ> anotherC(Consumer<δ> t, δ δ) {
		t.accept(δ);
		return empty();
	}

	/**
	 * @return Empty function
	 */
	public static <λ> Function<λ> empty() {
		return (λ) -> {};
	}

	/**
	 * Construct a {@link Function}, if the optional is present then we execute λ <blockquote>
	 * 
	 * <pre>
	 *     if(condition && o.isPresent) {
	 *        λ.execute
	 *        return emptyFunction // empty function for prevent double call w/ {@link Function#aldebaran(Object)} or {@link Function#andThen(Function)}
	 *     } else return λ // for simple chaining with andThen etc..
	 * 
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param λ
	 *            the function to exec if the optional is present
	 * @param o
	 *            the optional
	 * @param condition
	 *            a simple way to add a if
	 * @return λ if the optional is empty or an empty {@link Function} if the optional is present and executed
	 */
	public static <λ> Function<λ> of(Function<λ> λ, Option<λ> o, boolean condition) {
		return condition && !o.ifPresent(λ::aldebaran).isEmpty() ? empty() : λ;
	}

	/**
	 * Chain purpose
	 * 
	 * @see {@link Function#of(Function, Option, boolean)}
	 * @param λ
	 * @return
	 */
	public static <λ> Function<λ> of(Function<λ> λ) {
		return of(λ, Option.empty(), false);
	}

	/**
	 * exec function if condition
	 * 
	 * @see {@link Function#of(Function, Option, boolean)}
	 * @param f
	 * @param λ
	 * @param condition
	 * @return
	 */
	public static <λ> Function<λ> of(Function<λ> f, λ λ, boolean condition) {
		return of(f, Option.of(λ), condition);
	}

	/**
	 * exec function
	 * 
	 * @see {@link Function#of(Function, Option, boolean)}
	 * @param f
	 * @param λ
	 * @return
	 */
	public static <λ> Function<λ> of(Function<λ> f, λ λ) {
		return of(f, Option.of(λ), true);
	}

	/**
	 * Simple runnable run with chain
	 * 
	 * @param r
	 * @return
	 */
	public static <λ> Function<λ> ofVoid(Runnable r) {
		r.run();
		return empty();
	}

	/**
	 * Compact a BiConsumer into a consumer!
	 * 
	 * @param f
	 * @param a
	 * @return
	 */
	public static <A, B> Consumer<B> with(BiConsumer<A, B> f, A a) { // Carrefull ! the returned consumer must be the type of the last args (for logical purpose)
		return (b) -> f.accept(a, b);
	}

	public static <A, B, C> Consumer<C> with(TriConsumer<A, B, C> f, A a, B b) {
		return (c) -> f.accept(a, b, c);
	}

	public static <A, B, C, D> Consumer<D> with(TetraConsumer<A, B, C, D> f, A a, B b, C c) {
		return (d) -> f.accept(a, b, c, d);
	}

	public static <A, B, C, D, E> Consumer<E> with(PentaConsumer<A, B, C, D, E> f, A a, B b, C c, D d) {
		return (e) -> f.accept(a, b, c, d, e);
	}

	@FunctionalInterface
	interface TriConsumer<A, B, C> {
		void accept(A a, B b, C c);
	}

	@FunctionalInterface
	interface TetraConsumer<A, B, C, D> {
		void accept(A a, B b, C c, D d);
	}

	@FunctionalInterface
	interface PentaConsumer<A, B, C, D, E> {
		void accept(A a, B b, C c, D d, E e);
	}

	@FunctionalInterface
	interface HexaConsumer<A, B, C, D, E, F> {
		void accept(A a, B b, C c, D d, E e, F f);
	}

	@FunctionalInterface
	interface HeptaConsumer<A, B, C, D, E, F, G> {
		void accept(A a, B b, C c, D d, E e, F f, G g);
	}

	@FunctionalInterface
	interface OctaConsumer<A, B, C, D, E, F, G, H> {
		void accept(A a, B b, C c, D d, E e, F f, G g, H h);
	}

	@FunctionalInterface
	interface EnneaConsumer<A, B, C, D, E, F, G, H, I> {
		void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i);
	}

	@FunctionalInterface
	interface DecaConsumer<A, B, C, D, E, F, G, H, I, J> {
		void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j);
	}

}
