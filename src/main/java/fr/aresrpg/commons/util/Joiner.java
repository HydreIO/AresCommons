package fr.aresrpg.commons.util;

@FunctionalInterface
public interface Joiner<T> {
    T join(T t1 , T t2);
}
