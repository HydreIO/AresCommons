package fr.aresrpg.commons.domain.util.stream.bistream;

import java.util.Spliterator;
import java.util.function.Consumer;

public class BiSpliterator<T , U> implements Spliterator<BiStream.BiValue<T , U>>{

    private Spliterator<T> t;
    private Spliterator<U> u;

    BiSpliterator(Spliterator<T> t, Spliterator<U> u) {
        this.t = t;
        this.u = u;
    }

    @Override
    public boolean tryAdvance(Consumer<? super BiStream.BiValue<T, U>> action) {
        if (action == null)
            throw new NullPointerException();
        BiStream.BiValue<T , U> b = new BiStream.BiValue<>(null , null);
        boolean tr = this.t.tryAdvance(b::setT);
        boolean ur = this.u.tryAdvance(b::setU);
        if(!tr && !ur)
            return false;
        action.accept(b);
        return true;
    }

    @Override
    public Spliterator<BiStream.BiValue<T, U>> trySplit() {
        return new BiSpliterator<>(t.trySplit() , u.trySplit());
    }

    @Override
    public long estimateSize() {
        long ts = this.t.estimateSize();
        long us = this.u.estimateSize();
        return Math.max(ts , us);
    }

    @Override
    public int characteristics() {
        return t.characteristics() | u.characteristics();
    }
}