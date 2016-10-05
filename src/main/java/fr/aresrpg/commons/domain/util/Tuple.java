package fr.aresrpg.commons.domain.util;

import java.util.Objects;

/**
 * A Tuple is a structure that hold 3 values
 *
 * @param <F>
 *            First type contained in this Tuple
 * @param <S>
 *            Second type contained in this Tuple
 * @param <T>
 *            The Third type contained in this Tuple
 * @see Pair
 * @see ModifiableTuple
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class Tuple<F, S, T> extends Pair<F, S> {
	protected T third;

	/**
	 * Construct a new Tuple with this 3 values
	 *
	 * @param first
	 *            the first value of the Tuple
	 * @param second
	 *            the second value of the Tuple
	 * @param third
	 *            the third value of the Tuple
	 */
	public Tuple(F first, S second, T third) {
		super(first, second);
		this.third = third;
	}

	/**
	 * Construct a new Tuple with this pair and values
	 *
	 * @param pair
	 *            the pair to get the first and second value
	 * @param third
	 *            the third value of the Tuple
	 */
	public Tuple(Pair<F, S> pair, T third) {
		super(pair);
		this.third = third;
	}

	/**
	 * Construct a copy of the Tuple passed in argument
	 * 
	 * @param Tuple
	 *            the Tuple to copy
	 */
	public Tuple(Tuple<F, S, T> tuple) {
		super(tuple);
		this.third = third;
	}

	/**
	 * Get third value of this Tuple
	 * 
	 * @return the third value
	 */
	public T getThird() {
		return third;
	}

	/**
	 * Transform this Tuple to a modifiable version
	 * 
	 * @return a Modifiable Tuple with same values
	 */
	public ModifiableTuple<F, S, T> toModifiableTuple() {
		return new ModifiableTuple<>(first, second, third);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
		return Objects.equals(third, tuple.third);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), third);
	}
}
