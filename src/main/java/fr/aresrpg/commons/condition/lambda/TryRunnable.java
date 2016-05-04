package fr.aresrpg.commons.condition.lambda;

@FunctionalInterface
public interface TryRunnable {
    void run() throws Throwable;
}
