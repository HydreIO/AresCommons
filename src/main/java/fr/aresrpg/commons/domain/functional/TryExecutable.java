package fr.aresrpg.commons.domain.functional;

@FunctionalInterface
public interface TryExecutable {
    void execute() throws Exception;
}
