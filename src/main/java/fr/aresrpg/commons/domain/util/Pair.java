package fr.aresrpg.commons.domain.util;

public class Pair<F , S> {
	protected F first;
	protected S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}
}
