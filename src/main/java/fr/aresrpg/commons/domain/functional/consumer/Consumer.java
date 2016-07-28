package fr.aresrpg.commons.domain.functional.consumer;

@FunctionalInterface
public interface Consumer<T> {
	void accept(T a);

	default Consumer<T> then(Consumer<T> w) {
		return (T t) -> {
			accept(t);
			w.accept(t);
		};
	}

	static <T> Consumer<T> empty() {
		return t -> {};
	}

	default <A, B> BiConsumer<A, B> both(Consumer<A> wa, Consumer<B> wb) {
		return (A a, B b) -> {
			wa.accept(a);
			wb.accept(b);
		};
	}

	default java.util.function.Consumer<T> toNative() {
		return this::accept;
	}

	default <A, B> java.util.function.BiConsumer<A, B> toNativeBoth(Consumer<A> wa, Consumer<B> wb) {
		return (A a, B b) -> {
			wa.accept(a);
			wb.accept(b);
		};
	}
}
