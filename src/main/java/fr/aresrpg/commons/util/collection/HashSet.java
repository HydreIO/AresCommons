package fr.aresrpg.commons.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HashSet<E> extends java.util.HashSet<E> implements Set<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2249609191019766975L;

	public static class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements java.util.Set<E>, Serializable {

		public UnmodifiableSet(java.util.Set<? extends E> c) {
			super(c);
		}

		private static final long serialVersionUID = -9215047833775013803L;

		public boolean equals(Object o) {
			return o == this || c.equals(o);
		}

		public int hashCode() {
			return c.hashCode();
		}
	}

	static class UnmodifiableCollection<E> implements Collection<E>, Serializable {
		private static final long serialVersionUID = 1820017752578914078L;

		final Collection<? extends E> c;

		UnmodifiableCollection(Collection<? extends E> c) {
			if (c == null) throw new NullPointerException();
			this.c = c;
		}

		public int size() {
			return c.size();
		}

		public boolean isEmpty() {
			return c.isEmpty();
		}

		public boolean contains(Object o) {
			return c.contains(o);
		}

		public Object[] toArray() {
			return c.toArray();
		}

		public <T> T[] toArray(T[] a) {
			return c.toArray(a);
		}

		public String toString() {
			return c.toString();
		}

		public Iterator<E> iterator() {
			return new Iterator<E>() {
				private final Iterator<? extends E> i = c.iterator();

				public boolean hasNext() {
					return i.hasNext();
				}

				public E next() {
					return i.next();
				}

				public void remove() {
					throw new UnsupportedOperationException();
				}

				@Override
				public void forEachRemaining(Consumer<? super E> action) {
					// Use backing collection version
					i.forEachRemaining(action);
				}
			};
		}

		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		public boolean containsAll(Collection<?> coll) {
			return c.containsAll(coll);
		}

		public boolean addAll(Collection<? extends E> coll) {
			throw new UnsupportedOperationException();
		}

		public boolean removeAll(Collection<?> coll) {
			throw new UnsupportedOperationException();
		}

		public boolean retainAll(Collection<?> coll) {
			throw new UnsupportedOperationException();
		}

		public void clear() {
			throw new UnsupportedOperationException();
		}

		// Override default methods in Collection
		@Override
		public void forEach(Consumer<? super E> action) {
			c.forEach(action);
		}

		@Override
		public boolean removeIf(Predicate<? super E> filter) {
			throw new UnsupportedOperationException();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Spliterator<E> spliterator() {
			return (Spliterator<E>) c.spliterator();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Stream<E> stream() {
			return (Stream<E>) c.stream();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Stream<E> parallelStream() {
			return (Stream<E>) c.parallelStream();
		}
	}
}
