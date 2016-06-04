package fr.aresrpg.commons.domain.condition.functional;

@FunctionalInterface
public interface TryRunnable {
    void run() throws Exception;
}
