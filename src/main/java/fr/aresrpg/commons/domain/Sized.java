package fr.aresrpg.commons.domain;

import java.util.Iterator;

/**
 * Represent an object with size , so it contains object an can be converted to an array
 * @param <T> the type of the objects contained
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public interface Sized<T> extends Iterable<T>{
	/**
	 * Get the count of element hold by this object
	 * @return the count of element hold
	 */
	long size();

	/**
	 * Add all element hold by this object in an array
	 * <p>
	 * If the length of the array is less than the size of the object hold ,
	 * the array is fill with the maximum of elements
	 * <p>
	 * If the length of the array is bigger than the size of the object hold ,
	 * the array is fill with all of the elements
	 * @param array the array to add element
	 * @param <E> the type of object in this array
	 * @return an array with elements of this object inside
	 * @see #toArray(int)
	 * @see #toArray()
	 */
	@SuppressWarnings("unchecked")
	default <E> E[] toArray(E[] array){
		return Iterators.toArray((Iterator<E>)iterator() , array);
	}

	/**
	 * Create an array with the size passed in parameter and all objects contained by this object in it
	 * If the length of the array is less than the size of the object hold ,
	 * the array is fill with the maximum of elements
	 * <p>
	 * If the length of the array is bigger than the size of the object hold ,
	 * the array is fill with all of the elements
	 * @param size the size of the array to be created
	 * @param <E> the type of object in this array
	 * @return an array with elements of this object inside
	 * @see #toArray(Object[])
	 * @see #toArray(int)
	 */
	@SuppressWarnings("unchecked")
	default <E> E[] toArray(int size){
		return toArray((E[]) new Object[size]);
	}

	/**
	 * Create an array with the size of the element hold containing hold the elements hold by this object
	 * @return an array with all the elements hold by this object
	 * @see #toArray(Object[])
	 * @see #toArray(int)
	 */
	@SuppressWarnings("unchecked")
	default T[] toArray(){
		return toArray((int) size());
	}
}
