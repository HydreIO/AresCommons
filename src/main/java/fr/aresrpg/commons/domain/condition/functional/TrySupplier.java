package fr.aresrpg.commons.domain.condition.functional;

@FunctionalInterface
public interface TrySupplier<T> {
	T get() throws Exception;

}
