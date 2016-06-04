package fr.aresrpg.commons.domain.condition.functional.consumer;

public interface HexaConsumer<A, B, C, D, E, F> {
	void accept(A a, B b, C c, D d, E e, F f);

	default HexaConsumer<A, B, C, D, E, F> then(HexaConsumer<A, B, C, D, E, F> after) {
		return (a, b, c, d, e, f) -> {
			accept(a, b, c, d, e, f);
			after.accept(a, b, c, d, e, f);
		};
	}
}
