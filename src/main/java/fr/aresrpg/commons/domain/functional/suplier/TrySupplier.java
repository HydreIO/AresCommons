package fr.aresrpg.commons.domain.functional.suplier;

@FunctionalInterface
public interface TrySupplier<T> {
	T get() throws Exception;

}
