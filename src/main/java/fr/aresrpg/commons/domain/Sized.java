package fr.aresrpg.commons.domain;

import java.util.Iterator;

public interface Sized<T> extends Iterable<T>{
	long size();

	@SuppressWarnings("unchecked")
	default <E> E[] toArray(E[] array){
		return Iterators.toArray((Iterator<E>)iterator() , array);
	}

	@SuppressWarnings("unchecked")
	default <E> E[] toArray(int size){
		return toArray((E[]) new Object[size]);
	}

	@SuppressWarnings("unchecked")
	default T[] toArray(){
		return toArray((int) size());
	}
}
