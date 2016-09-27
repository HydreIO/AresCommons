package fr.aresrpg.commons.domain.functional.suplier;

@FunctionalInterface
public interface Supplier<T> extends TrySupplier<T> {
	@Override
	T get();

	default java.util.function.Supplier<T> toNative() {
		return this::get;
	}
}
