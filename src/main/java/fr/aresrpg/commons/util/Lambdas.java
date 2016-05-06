package fr.aresrpg.commons.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Lambdas {

	public static <T> Consumer<T> emptyConsumer(Consumer<T> t) {
		return t;
	}

	public static <T> Supplier<T> emptySupplier(Supplier<T> t) {
		return t;
	}

	public static <A, B> BiConsumer<A, B> emptyBiConsumer(BiConsumer<A, B> t) {
		return t;
	}

}
