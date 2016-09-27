package fr.aresrpg.commons.domain.container.empty;

import fr.aresrpg.commons.domain.container.Container;
import fr.aresrpg.commons.domain.util.Iterators;

import java.util.Iterator;

/**
 * An empty container
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class EmptyContainer implements Container {

	/**
	 * The instance of the empty container
	 */
	public static final Container INSTANCE = new EmptyContainer();

	private EmptyContainer() {
	}

	@Override
	public boolean unsafeRemove(Object o) {
		return false;
	}

	@Override
	public boolean unsafeRemoveAll(Iterable o) {
		return false;
	}

	@Override
	public boolean unsafeAdd(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean unsafeAddAll(Iterable o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean unsafeContains(Object o) {
		return false;
	}

	@Override
	public boolean unsafeContainsAll(Iterable o) {
		return false;
	}

	@Override
	public int unsafeContainsCount(Iterable o) {
		return 0;
	}

	@Override
	public long size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Iterator iterator() {
		return Iterators.empty();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isImmutable() {
		return true;
	}

	@Override
	public boolean isConcurrent() {
		return true;
	}
}
