package fr.aresrpg.commons.domain.util;

/**
 * A Turple is a structure that hold 3 values
 * @param <F> First type contained in this turple
 * @param <S> Second type contained in this turple
 * @param <T> The Third type contained in this turple
 * @see Pair
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class ModifiableTurple<F , S , T> extends Turple<F , S , T>{
	/**
	 * {@inheritDoc}
	 */
	public ModifiableTurple(F first, S second, T third) {
		super(first, second, third);
	}

	/**
	 * {@inheritDoc}
	 */
	public ModifiableTurple(Pair<F, S> pair, T third) {
		super(pair, third);
	}

	/**
	 * Set the first value of this turple
	 * @param first the first value to set
	 */
	public void setFirst(F first){
		this.first = first;
	}

	/**
	 * Set the second value of this turple
	 * @param second the second value to set
	 */
	public void setSecond(S second){
		this.second = second;
	}

	/**
	 * Set the third value of this turple
	 * @param third the third value to set
	 */
	public void setThird(T third) {
		this.third = third;
	}

	@Override
	public ModifiableTurple<F, S, T> toModifiableTurple() {
		return this;
	}
}
