package fr.aresrpg.commons.domain;

import fr.aresrpg.commons.domain.util.IllegalConstructionException;

import java.util.*;

/**
 * An util class to use with iterators
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class Iterators {
	private static final Iterator<?> EMPTY_ITERATOR = new EmptyIterator();

	private Iterators() {throw new IllegalConstructionException();}

	/**
	 * Get an empty iterator , it contain nothing
	 * @param <T> the type of the iterator
	 * @return an empty iterator
	 */
	@SuppressWarnings("unchecked")
	public static <T> Iterator<T> empty() {
		return (Iterator<T>) EMPTY_ITERATOR;
	}

	/**
	 * Get an iterator of the provided values
	 * @param values values with the iterator , must contains
	 * @param <T> the type of the iterator
	 * @return an iterator of the provided values
	 */
	@SafeVarargs
	public static <T> Iterator<T> of(T... values) {
		return new ArrayIterator<>(values);
	}

	/**
	 * Add all iterator value in an array
	 * @param iterator the iterator to get the values
	 * @param array the array to add value
	 * @param <T> the type of the iterator
	 * @return an array with iterator value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Iterator<T> iterator, T[] array) {
		for (int i = 0; iterator.hasNext() && i < array.length; i++)
			array[i] = iterator.next();
		return array;
	}

	/**
	 * Transform an iterator into a sorted iterator
	 * @param it the iterator with must be sorted
	 * @param comparator the comparator to sort the iterator
	 * @param <T> the type of the iterator
	 * @return a sorted iterator
	 */
	public static <T> Iterator<T> sortedIterator(Iterator<T> it, Comparator<? super T> comparator) {
		List<T> list = new ArrayList<>();
		while (it.hasNext())
			list.add(it.next());

		Collections.sort(list, comparator);
		return list.iterator();
	}

	private static class EmptyIterator implements Iterator<Object> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Object next() {
			throw new NoSuchElementException();
		}
	}

	private static class ArrayIterator<T> implements Iterator<T> {
		private final T[] array;
		private volatile int i;

		public ArrayIterator(T[] array) {
			this.array = array;
			this.i = 0;
		}

		@Override
		public boolean hasNext() {
			return i < array.length;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			else return array[i++];
		}
	}
}
