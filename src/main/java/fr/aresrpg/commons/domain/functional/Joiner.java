package fr.aresrpg.commons.domain.functional;

@FunctionalInterface
/**
 * A function interface to join to objects
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public interface Joiner<T> {
	/**
     * Join this two values
     * @param t1 the first value
     * @param t2 the value to join with the first
     * @return a value of the two values joined
     */
    T join(T t1 , T t2);
}