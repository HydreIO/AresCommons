package fr.aresrpg.commons.domain.util;

import java.util.Objects;

public class Turple<F , S , T> extends Pair<F , S>{
	protected T third;
	public Turple(F first, S second , T third) {
		super(first, second);
		this.third = third;
	}

	public T getThird() {
		return third;
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
