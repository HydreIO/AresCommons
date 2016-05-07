package fr.aresrpg.commons.util.function;

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
		return ofVoid(r, true);
	}

	public static <λ> Function<λ> ofVoid(Runnable r, boolean condition) {
		if (condition) r.run();
		return empty();
	}

}
