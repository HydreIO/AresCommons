package fr.aresrpg.commons.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UnmodifiableCollection<E> implements Collection<E>, Serializable {
	private static final long serialVersionUID = 1820017752578914078L;

	protected final transient Collection<E> delegate;

	public UnmodifiableCollection(Collection<E> delegate) {
		if (delegate == null) throw new NullPointerException();
		this.delegate = delegate;
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private final Iterator<? extends E> i = delegate.iterator();

			@Override public boolean hasNext() {
				return i.hasNext();
			}

			@Override public E next() {
				return i.next();
			}

			@Override public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void forEachRemaining(Consumer<? super E> action) {
				i.forEachRemaining(action);// Use backing collection version
			}
		};
	}
	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		return delegate.containsAll(coll);
	}

	@Override
	public boolean addAll(Collection<? extends E> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	// Override default methods in Collection
	@Override
	public void forEach(Consumer<? super E> action) {
		delegate.forEach(action);
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spliterator<E> spliterator() {
		return delegate.spliterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<E> stream() {
		return delegate.stream();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<E> parallelStream() {
		return delegate.parallelStream();
	}

	public static class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements java.util.Set<E>, Serializable {

		private static final long serialVersionUID = -9215047833775013803L;

		public UnmodifiableSet(java.util.Set<E> delegate) {
			super(delegate);
		}

		@Override
		public boolean equals(Object o) {
			return o == this || delegate.equals(o);
		}

		@Override
		public int hashCode() {
			return delegate.hashCode();
		}
	}
}
