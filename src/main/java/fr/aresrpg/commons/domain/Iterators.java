package fr.aresrpg.commons.domain;

import fr.aresrpg.commons.domain.condition.functional.suplier.Supplier;

import java.util.*;

public class Iterators {
    private static final Iterator<?> EMPTY_ITERATOR = new EmptyIterator();
    private Iterators(){}

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> empty(){
        return (Iterator<T>) EMPTY_ITERATOR;
    }

	@SafeVarargs
	public static <T> Iterator<T> of(T ... values){
        return new ArrayIterator<>(values);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterator<T> iterator, T[] array) {
        for(int i = 0 ; iterator.hasNext() && i < array.length ; i++)
            array[i] = iterator.next();
        return array;
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

    public static <T> Iterator<T> sortedIterator(Iterator<T> it, Comparator<? super T> comparator) {
        List<T> list = new ArrayList<>();
        while (it.hasNext())
            list.add(it.next());

        Collections.sort(list, comparator);
        return list.iterator();
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
            if(!hasNext())
                throw new NoSuchElementException();
            else
                return array[i++];
        }
    }
}
