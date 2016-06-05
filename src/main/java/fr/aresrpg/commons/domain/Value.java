package fr.aresrpg.commons.domain;

import java.util.StringJoiner;

public interface Value<T> extends Iterable<T>{
    default void debug(){

    }

    default String stringify(){
        StringJoiner joiner = new StringJoiner(", ");
        for(T value : this)
            joiner.add(String.valueOf(value));

        return objectName()+"["+joiner.toString()+"]";
    }

    default String objectName(){
        return getClass().getName();
    }

    boolean isEmpty();

    T get();
}
