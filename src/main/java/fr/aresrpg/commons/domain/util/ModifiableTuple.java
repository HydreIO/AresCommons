package fr.aresrpg.commons.domain.util;

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
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class ModifiableTuple<F, S, T> extends Tuple<F, S, T> {

	/**
	 * {@inheritDoc}
	 */
	public ModifiableTuple(F first, S second, T third) {
		super(first, second, third);
	}

	/**
	 * {@inheritDoc}
	 */
	public ModifiableTuple(Pair<F, S> pair, T third) {
		super(pair, third);
	}

	/**
	 * Set the first value of this Tuple
	 * 
	 * @param first
	 *            the first value to set
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * Set the second value of this Tuple
	 * 
	 * @param second
	 *            the second value to set
	 */
	public void setSecond(S second) {
		this.second = second;
	}

	/**
	 * Set the third value of this Tuple
	 * 
	 * @param third
	 *            the third value to set
	 */
	public void setThird(T third) {
		this.third = third;
	}

	@Override
	public ModifiableTuple<F, S, T> toModifiableTuple() {
		return this;
	}
}
