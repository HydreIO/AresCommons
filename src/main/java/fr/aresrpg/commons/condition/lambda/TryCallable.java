package fr.aresrpg.commons.condition.lambda;

@FunctionalInterface
public interface TryCallable<T> {
    T call() throws Throwable;
}
