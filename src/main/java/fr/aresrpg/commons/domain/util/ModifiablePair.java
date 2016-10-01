package fr.aresrpg.commons.domain.util;

/**
 * A pair is a structure that hold 2 values
 *
 * @param <F>
 *            First type contained in this pair
 * @param <S>
 *            Second type contained in this pair
 * @see Tuple
 * @see ModifiablePair
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ModifiablePair<F, S> extends Pair<F, S> {
	/**
	 * {@inheritDoc}
	 */
	public ModifiablePair(F first, S second) {
		super(first, second);
	}

	/**
	 * {@inheritDoc}
	 */
	public ModifiablePair(Pair<F, S> pair) {
		super(pair);
	}

	/**
	 * Set the first value of this pair
	 * 
	 * @param first
	 *            the first value
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * Set the second value of this pair
	 * 
	 * @param second
	 *            the second value
	 */
	public void setSecond(S second) {
		this.second = second;
	}

	@Override
	public ModifiablePair<F, S> toModifiable() {
		return this;
	}
}
