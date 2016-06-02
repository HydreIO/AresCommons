package fr.aresrpg.commons.util;

public class Turple<F , S , T> extends Pair<F , S>{
	protected T third;
	public Turple(F first, S second , T third) {
		super(first, second);
		this.third = third;
	}

	public T getThird() {
		return third;
	}
}
