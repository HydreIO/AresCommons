package fr.aresrpg.commons.domain.util.collection;

import java.util.Collection;

public interface Set<E> extends java.util.Set<E> { // NOSONAR rename this interface

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public boolean remove(Object o);

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public boolean removeAll(Collection<?> c);

	default boolean safeRemove(E e) {
		return remove(e);
	}

	default boolean safeRemoveAll(Collection<? super E> c) {
		return removeAll(c);
	}

	static <E> Set<E> empty() {
		return new HashSet<>();
	}

}
