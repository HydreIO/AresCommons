package fr.aresrpg.commons.condition.functional;

@FunctionalInterface
public interface TryCallable<T> {
    T call() throws Throwable;
}
