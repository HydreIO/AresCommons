package fr.aresrpg.commons.domain.container.singleton;

import fr.aresrpg.commons.domain.container.Container;
import fr.aresrpg.commons.domain.util.Iterators;
import fr.aresrpg.commons.domain.functional.suplier.Supplier;

import java.util.Iterator;
import java.util.Objects;

/**
 * An singleton container
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface LambdaSingletonContainer<E> extends Container<E> , Supplier<E> {
	@Override
	default boolean unsafeRemove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean unsafeRemoveAll(Iterable o){
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean unsafeAdd(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean unsafeAddAll(Iterable o) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean unsafeContains(Object o) {
		return Objects.equals(o , get());
	}

	@Override
	@SuppressWarnings("unchecked")
	default boolean unsafeContainsAll(Iterable o) {
		Iterator it = o.iterator();
		while(it.hasNext())
			if(!Objects.equals(it.next() , get()))
				return false;
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	default int unsafeContainsCount(Iterable o) {
		Iterator it = o.iterator();
		int i = 0;
		while(it.hasNext())
			if(Objects.equals(it.next() , get()))
				i++;
		return i;
	}

	@Override
	default long size() {
		return 1;
	}

	@Override
	default boolean isEmpty() {
		return false;
	}

	@Override
	default Iterator<E> iterator() {
		return Iterators.of(get());
	}

	@Override
	default void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean isImmutable(){
		return true;
	}

	@Override
	default boolean isConcurrent(){
		return true;
	}
}
