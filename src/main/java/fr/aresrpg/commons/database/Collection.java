package fr.aresrpg.commons.database;

public interface Collection<T> {
    T put(T value);

    T update(Filter filter , T value);

    T putOrUpdate(Filter filter , T value);

    T[] find(Filter filter , int max);

    default T[] find(Filter filter){
        return find(filter , Integer.MAX_VALUE);
    }

    void remove(Filter filter , int removed);

    default void remove(Filter filter){
        remove(filter , 1);
    }

    default boolean exist(Filter filter){
        return find(filter).length != 0;
    }
}
