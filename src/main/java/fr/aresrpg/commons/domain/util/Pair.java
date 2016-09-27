package fr.aresrpg.commons.domain.util;

import fr.aresrpg.commons.domain.functional.consumer.BiConsumer;
import fr.aresrpg.commons.domain.functional.consumer.Consumer;

import java.util.Objects;

/**
 * A pair is a structure that hold 2 values
 *
 * @param <F>
 *            First type contained in this pair
 * @param <S>
 *            Second type contained in this pair
 * @see Tuple
 * @see Or
 * @see ModifiablePair
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Pair<F, S> {
	protected F first;
	protected S second;

	/**
	 * Construct a new pair with two values
	 * 
	 * @param first
	 *            the first value
	 * @param second
	 *            the second value
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Perform actions on the first element
	 * 
	 * @param cons
	 *            the consumer
	 */
	public void consumeFirst(Consumer<F> cons) {
		cons.accept(getFirst());
	}

	/**
	 * Perform actions on the second element
	 * 
	 * @param cons
	 *            the consumer
	 */
	public void consumeSecond(Consumer<S> cons) {
		cons.accept(getSecond());
	}

	/**
	 * Perform actions on both elements
	 * 
	 * @param cons
	 *            the consumer
	 */
	public void consumeBoth(BiConsumer<F, S> cons) {
		cons.accept(getFirst(), getSecond());
	}

	/**
	 * Construct a new pair containing the values of the given pair
	 * 
	 * @param pair
	 *            the pair to copy
	 */
	public Pair(Pair<F, S> pair) {
		this.first = pair.first;
		this.second = pair.second;
	}

	/**
	 * Get the first value hold by this pair
	 * 
	 * @return the first value hold by this pair
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * Get the second value hold by this pair
	 * 
	 * @return the second value hold by this pair
	 */
	public S getSecond() {
		return second;
	}

	/**
	 * Transform this pair to a modifiable pair
	 * 
	 * @return a modifiable version of this pair
	 */
	public ModifiablePair<F, S> toModifiable() {
		return new ModifiablePair<>(first, second);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair<?, ?> pair = (Pair<?, ?>) o;
		return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
