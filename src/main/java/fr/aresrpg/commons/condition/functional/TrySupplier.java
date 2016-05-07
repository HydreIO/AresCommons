package fr.aresrpg.commons.condition.functional;

@FunctionalInterface
public interface TrySupplier<T> {
    T call() throws Throwable;
}
