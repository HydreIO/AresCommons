package fr.aresrpg.commons.domain.util.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import fr.aresrpg.commons.domain.util.collection.UnmodifiableCollection;
import fr.aresrpg.commons.domain.util.map.HashMap;
import fr.aresrpg.commons.domain.util.map.Map;

public class Collectors {

	private static final Set<Collector.Characteristics> CH_UNORDERED_ID = new UnmodifiableCollection.UnmodifiableSet<>(EnumSet.of(Collector.Characteristics.UNORDERED,
			Collector.Characteristics.IDENTITY_FINISH));

	private static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));

	private Collectors(){}

	public static <T> Collector<T, ?, fr.aresrpg.commons.domain.util.collection.Set<T>> toSet() {
		return new CollectorImpl<>((Supplier<Set<T>>) HashSet::new, Set<T>::add, (left, right) -> {
			left.addAll(right);
			return left;
		}, CH_UNORDERED_ID);
	}

	private static <K, V, M extends Map<K, V>> BinaryOperator<M> mapMerger(BinaryOperator<V> mergeFunction) {
		return (m1, m2) -> {
			for (Map.Entry<K, V> e : m2.entrySet())
				m1.merge(e.getKey(), e.getValue(), mergeFunction);
			return m1;
		};
	}

	private static <T> BinaryOperator<T> throwingMerger() {
		return (u, v) -> {
			throw new IllegalStateException(String.format("Duplicate key %s", u));
		};
	}

	public static <T,  K, U> Collector<T, ? , Map<K, U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
		return toMap(keyMapper, valueMapper, throwingMerger(), HashMap::new);
	}

	public static <T, K, U, M extends Map<K, U>> Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction,
			Supplier<M> mapSupplier) {
		BiConsumer<M, T> accumulator = (map, element) -> map.merge(keyMapper.apply(element), valueMapper.apply(element), mergeFunction);
		return new CollectorImpl<>(mapSupplier, accumulator, mapMerger(mergeFunction), CH_ID);
	}

	public static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
		private final Supplier<A> supplier;
		private final BiConsumer<A, T> accumulator;
		private final BinaryOperator<A> combiner;
		private final Function<A, R> finisher;
		private final Set<Characteristics> characteristics;

		public CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher, Set<Characteristics> characteristics) {
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
			this.finisher = finisher;
			this.characteristics = characteristics;
		}

		public CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Set<Characteristics> characteristics) {
			this(supplier, accumulator, combiner, castingIdentity(), characteristics);
		}

		@SuppressWarnings("unchecked")
		private static <I, R> Function<I, R> castingIdentity() {
			return i -> (R) i;
		}

		@Override
		public BiConsumer<A, T> accumulator() {
			return accumulator;
		}

		@Override
		public Supplier<A> supplier() {
			return supplier;
		}

		@Override
		public BinaryOperator<A> combiner() {
			return combiner;
		}

		@Override
		public Function<A, R> finisher() {
			return finisher;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return characteristics;
		}
	}

}
