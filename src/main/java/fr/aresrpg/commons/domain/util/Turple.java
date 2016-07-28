package fr.aresrpg.commons.domain.util;

import java.util.Objects;

/**
 * A Turple is a structure that hold 3 values
 *
 * @param <F> First type contained in this turple
 * @param <S> Second type contained in this turple
 * @param <T> The Third type contained in this turple
 * @see Pair
 * @see ModifiableTurple
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class Turple<F , S , T> extends Pair<F , S>{
	protected T third;

	/**
	 * Construct a new turple with this 3 values
	 *
	 * @param first the first value of the turple
	 * @param second the second value of the turple
	 * @param third the third value of the turple
	 */
	public Turple(F first, S second , T third) {
		super(first, second);
		this.third = third;
	}

	/**
	 * Construct a new turple with this pair and values
	 *
	 * @param pair the pair to get the first and second value
	 * @param third the third value of the turple
	 */
	public Turple(Pair<F , S> pair , T third) {
		super(pair);
		this.third = third;
	}


	/**
	 * Construct a copy of the turple passed in argument
	 * @param turple the turple to copy
	 */
	public Turple(Turple<F , S , T> turple){
		super(turple);
		this.third = third;
	}

	/**
	 * Get third value of this turple
	 * @return the third value
	 */
	public T getThird() {
		return third;
	}

	/**
	 * Transform this Turple to a modifiable version
	 * @return a Modifiable turple with same values
	 */
	public ModifiableTurple<F , S , T> toModifiableTurple(){
		return new ModifiableTurple<>(first , second , third);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Turple<?, ?, ?> turple = (Turple<?, ?, ?>) o;
		return Objects.equals(third, turple.third);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), third);
	}
}
