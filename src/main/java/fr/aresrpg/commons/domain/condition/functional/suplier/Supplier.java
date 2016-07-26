package fr.aresrpg.commons.domain.condition.functional.suplier;

@FunctionalInterface
public interface Supplier<T> extends TrySupplier<T>{
	@Override
	T get();
}
