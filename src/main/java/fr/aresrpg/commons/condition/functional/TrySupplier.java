package fr.aresrpg.commons.condition.functional;

@FunctionalInterface
public interface TrySupplier<T> {
	T get() throws Throwable;

}
