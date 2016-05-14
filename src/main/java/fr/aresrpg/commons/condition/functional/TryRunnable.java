package fr.aresrpg.commons.condition.functional;

@FunctionalInterface
public interface TryRunnable {
    void run() throws Exception;
}
