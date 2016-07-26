package fr.aresrpg.commons.domain.condition.functional.suplier;

@FunctionalInterface
public interface TrySupplier<T> {
	T get() throws Exception;

}
