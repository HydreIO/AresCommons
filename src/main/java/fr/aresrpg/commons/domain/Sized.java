package fr.aresrpg.commons.domain;

import fr.aresrpg.commons.domain.util.Iterators;

import java.util.Iterator;

/**
 * Represent an object with a size, so it contains object an can be converted to an array
 * 
 * @param <T>
 *            the type of the objects contained
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Sized<T> extends Iterable<T> {
	/**
	 * Get the count of element hold by this object , this value can't be smaller than 0
	 * 
	 * @return the count of element hold
	 */
	long size();

	/**
	 * Fill the given array with this object
	 * </p>
	 * If the length of the array is smaller than the size of the object hold, then
	 * the array is filled with the maximum of elements
	 * </p>
	 * If the length of the array is bigger than the size of the object hold, then
	 * the array is filled with all the elements
	 * </p>
	 * 
	 * @param array
	 *            the array to add element
	 * @param <E>
	 *            the type of object in this array
	 * @return an array with elements of this object inside
	 * @see #toArray(int)
	 * @see #toArray()
	 */
	@SuppressWarnings("unchecked")
	default <E> E[] toArray(E[] array) {
		return Iterators.toArray((Iterator<E>) iterator(), array);
	}

	/**
	 * Create an array with a given size which contains this object
	 * </p>
	 * If the length of the array is smaller than the size of the object hold, then
	 * the array is filled with the maximum of elements
	 * </p>
	 * If the length of the array is bigger than the size of the object hold, then
	 * the array is filled with all the elements
	 * </p>
	 * 
	 * @param size
	 *            the size of the array to be created
	 * @param <E>
	 *            the type of object in this array
	 * @return an array with elements of this object inside
	 * @see #toArray(Object[])
	 * @see #toArray(int)
	 */
	@SuppressWarnings("unchecked")
	default <E> E[] toArray(int size) {
		return toArray((E[]) new Object[size]);
	}

	/**
	 * Transform this object into an array
	 * 
	 * @return an array containing all the elements hold by this object
	 * @see #toArray(Object[])
	 * @see #toArray(int)
	 */
	@SuppressWarnings("unchecked")
	default T[] toArray() {
		return toArray((int) size());
	}
}
