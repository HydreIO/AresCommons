package fr.aresrpg.commons.util.stream;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import fr.aresrpg.commons.util.collection.UnmodifiableCollection;

public class Collectors {

	static final Set<Collector.Characteristics> CH_UNORDERED_ID = new UnmodifiableCollection.UnmodifiableSet<>(EnumSet.of(Collector.Characteristics.UNORDERED,
			Collector.Characteristics.IDENTITY_FINISH));

	public static <T> Collector<T, ?, fr.aresrpg.commons.util.collection.Set<T>> toSet() {
		return new CollectorImpl<>((Supplier<Set<T>>) HashSet::new, Set<T>::add, (left, right) -> {
			left.addAll(right);
			return left;
		}, CH_UNORDERED_ID);
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
