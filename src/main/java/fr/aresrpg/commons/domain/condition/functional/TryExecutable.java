package fr.aresrpg.commons.domain.condition.functional;

@FunctionalInterface
public interface TryExecutable {
    void execute() throws Exception;
}
