package fr.aresrpg.commons.util.stream.bistream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class BiStreamImpl<T , U> implements BiStream<T , U> {

    private Stream<BiStream.BiValue<T , U>> stream;

    BiStreamImpl(Stream<BiValue<T, U>> stream) {
        this.stream = stream;
    }

    @Override
    public BiStream<T, U> filter(BiPredicate<? super T, ? super U> predicate) {
        return BiStream.wrap(stream.filter(v -> predicate.test(v.getT() , v.getU())));
    }

    @Override
    public <R> Stream<R> map(BiFunction<? super T, ? super U, ? extends R> function) {
        return stream.map(v -> function.apply(v.getT() , v.getU()));
    }

    @Override
    public IntStream mapToInt(ToIntBiFunction<? super T, ? super U> toIntBiFunction) {
        return stream.mapToInt(v -> toIntBiFunction.applyAsInt(v.getT() , v.getU()));
    }

    @Override
    public LongStream mapToLong(ToLongBiFunction<? super T, ? super U> toLongBiFunction) {
        return stream.mapToLong(v -> toLongBiFunction.applyAsLong(v.getT() , v.getU()));
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleBiFunction<? super T, ? super U> toDoubleBiFunction) {
        return stream.mapToDouble(v -> toDoubleBiFunction.applyAsDouble(v.getT() , v.getU()));
    }

    @Override
    public <R> Stream<R> flatMap(BiFunction<? super T, ? super U, ? extends Stream<? extends R>> function) {
        return stream.flatMap(v -> function.apply(v.getT() , v.getU()));
    }


    @Override
    public IntStream flatMapToInt(BiFunction<? super T, ? super U, ? extends IntStream> function) {
        return stream.flatMapToInt(v -> function.apply(v.getT() , v.getU()));
    }

    @Override
    public LongStream flatMapToLong(BiFunction<? super T, ? super U, ? extends LongStream> function) {
        return stream.flatMapToLong(v -> function.apply(v.getT() , v.getU()));
    }

    @Override
    public DoubleStream flatMapToDouble(BiFunction<? super T, ? super U ,? extends DoubleStream> function) {
        return stream.flatMapToDouble(v -> function.apply(v.getT() , v.getU()));
    }

    @Override
    public BiStream<T, U> distinct() {
        return BiStream.wrap(stream.distinct());
    }

    @Override
    public BiStream<T, U> sorted() {
        return BiStream.wrap(stream.sorted());
    }

    @Override
    public BiStream<T , U> sorted(Comparator<? super BiValue<T, U>> comparator) {
        return BiStream.wrap(stream.sorted(comparator));
    }

    @Override
    public BiStream<T, U> peek(BiConsumer<? super T, ? super U> consumer) {
        return BiStream.wrap(stream.peek(v -> consumer.accept(v.getT() , v.getU())));
    }

    @Override
    public BiStream<T, U> limit(long maxSize) {
        return BiStream.wrap(stream.limit(maxSize));
    }

    @Override
    public BiStream<T, U> skip(long n) {
        return BiStream.wrap(stream.skip(n));
    }


    @Override
    public void forEach(BiConsumer<? super T, ? super U> consumer) {
        stream.forEach(v -> consumer.accept(v.getT() , v.getU()));
    }

    @Override
    public void forEachOrdered(BiConsumer<? super T, ? super U> consumer) {
        stream.forEachOrdered(v -> consumer.accept(v.getT() , v.getU()));
    }

    @Override
    public Object[] toArray() {
        return stream.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> intFunction) {
        return stream.toArray(intFunction);
    }

    @Override
    public BiValue<T,U> reduce(BiValue<T,U>identity, BinaryOperator<BiValue<T,U>> accumulator) {
        return stream.reduce(identity , accumulator);
    }

    @Override
    public Optional<BiValue<T,U>> reduce(BinaryOperator<BiValue<T,U>> accumulator) {
        return stream.reduce(accumulator);
    }

    @Override
    public <R> R reduce(R identity, BiFunction<R, ? super BiValue<T,U>, R> accumulator, BinaryOperator<R> combiner) {
        return stream.reduce(identity , accumulator , combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super BiValue<T, U>> biConsumer, BiConsumer<R, R> biConsumer1) {
        return stream.collect(supplier , biConsumer , biConsumer1);
    }

    @Override
    public <R, A> R collect(Collector<? super BiValue<T, U>, A, R> collector) {
        return stream.collect(collector);
    }

    @Override
    public Optional<BiValue<T, U>> min(Comparator<? super BiValue<T, U>> comparator) {
        return stream.min(comparator);
    }

    @Override
    public Optional<BiValue<T, U>> max(Comparator<? super BiValue<T, U>> comparator) {
        return stream.max(comparator);
    }


    @Override
    public long count() {
        return stream.count();
    }

    @Override
    public boolean anyMatch(BiPredicate<? super T, ? super U> predicate) {
        return stream.anyMatch(v -> predicate.test(v.getT() , v.getU()));
    }

    @Override
    public boolean allMatch(BiPredicate<? super T, ? super U> predicate) {
        return stream.allMatch(v -> predicate.test(v.getT() , v.getU()));
    }

    @Override
    public boolean noneMatch(BiPredicate<? super T, ? super U> predicate) {
        return stream.noneMatch(v -> predicate.test(v.getT() , v.getU()));
    }

    @Override
    public Optional<BiValue<T, U>> findFirst() {
        return stream.findFirst();
    }

    @Override
    public Optional<BiValue<T, U>> findAny() {
        return stream.findAny();
    }

    @Override
    public Iterator<BiValue<T, U>> iterator() {
        return stream.iterator();
    }

    @Override
    public Spliterator<BiValue<T, U>> spliterator() {
        return stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }

    @Override
    public BiStream<T, U> sequential() {
        stream.sequential();
        return this;
    }

    @Override
    public BiStream<T, U> parallel() {
        stream.parallel();
        return this;
    }

    @Override
    public BiStream<T, U> unordered() {
        stream.unordered();
        return this;
    }

    @Override
    public BiStream<T, U> onClose(Runnable runnable) {
        stream.onClose(runnable);
        return this;
    }

    @Override
    public void close() {
        stream.close();
    }
}