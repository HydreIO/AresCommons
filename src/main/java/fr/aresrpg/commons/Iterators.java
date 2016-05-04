package fr.aresrpg.commons;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterators {
    private static final Iterator<?> EMPTY_ITERATOR = new EmptyIterator();
    private Iterators(){}

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> empty(){
        return (Iterator<T>) EMPTY_ITERATOR;
    }

    public static <T> Iterator<T> of(T ... values){
        return new ArrayIterator<>(values);
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
            if(!hasNext())
                throw new NoSuchElementException();
            else
                return array[i++];
        }
    }
}
