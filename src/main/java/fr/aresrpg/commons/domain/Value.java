package fr.aresrpg.commons.domain;

import java.util.StringJoiner;

/**
 * This class is a subset of the java object , a
 * lot of AresCommons objects implement it so you use this methods
 * @param <T> the value old
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Value<T> extends Iterable<T>{
    default void debug(){}

	/**
	 * Transform this object to a String for debug
	 * @return a nice String representation of this object
	 */
    default String stringify(){
        StringJoiner joiner = new StringJoiner(", ");
        for(T value : this)
            joiner.add(String.valueOf(value));

        return objectName()+"["+joiner.toString()+"]";
    }


	/**
	 * Get a nice the object name by default it return the class name
	 * @return the object name
	 */
    default String objectName(){
        return getClass().getName();
    }

	/**
	 * Check if this object is empty
	 * @return true if this object is empty
	 */
    boolean isEmpty();


	static String toString(Object o){
		if(o instanceof Value)
			return ((Value) o).stringify();
		else
			return o.toString();
	}
}
