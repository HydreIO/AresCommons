package fr.aresrpg.commons.domain.builder;

@FunctionalInterface
public interface Builder<T> {
    T build();
}
