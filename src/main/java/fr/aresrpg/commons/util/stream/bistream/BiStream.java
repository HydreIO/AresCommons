package fr.aresrpg.commons.util.stream.bistream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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
import java.util.stream.BaseStream;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface BiStream<T, U> extends BaseStream<BiStream.BiValue<T, U>, BiStream<T, U>> {

	/**
	 * A Value in a BiStream
	 * 
	 * @param <T>
	 *            The First value of BiStream
	 * @param <U>
	 *            The Second value of BiStream
	 */
	class BiValue<T, U> {
		/**
		 * The First value of BiStream
		 */
		private T t;

		/**
		 * The Second value of BiStream
		 */
		private U u;

		/**
		 * Create a new BiValue
		 * 
		 * @param t
		 *            The First value of BiStream
		 * @param u
		 *            The Second value of BiStream
		 */
		public BiValue(T t, U u) {
			this.t = t;
			this.u = u;
		}

		public T getT() {
			return t;
		}

		public U getU() {
			return u;
		}

		public void setT(T t) {
			this.t = t;
		}

		public void setU(U u) {
			this.u = u;
		}
	}

	/**
	 * Returns a stream consisting of the elements of this stream that match the given predicate.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param predicate
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> predicate to apply to each element to determine if it should be included
	 * @return the new stream
	 */
	BiStream<T, U> filter(BiPredicate<? super T, ? super U> predicate);

	/**
	 * Returns a stream consisting of the results of applying the given function to the elements of this stream.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param <R>
	 *            The element type of the new stream
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element
	 * @return the new stream
	 */
	<R> Stream<R> map(BiFunction<? super T, ? super U, ? extends R> mapper);

	/**
	 * Returns an {@code IntStream} consisting of the results of applying the given function to the elements of this stream.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps"> intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element
	 * @return the new stream
	 */
	IntStream mapToInt(ToIntBiFunction<? super T, ? super U> mapper);

	/**
	 * Returns a {@code LongStream} consisting of the results of applying the given function to the elements of this stream.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element
	 * @return the new stream
	 */
	LongStream mapToLong(ToLongBiFunction<? super T, ? super U> mapper);

	/**
	 * Returns a {@code DoubleStream} consisting of the results of applying the given function to the elements of this stream.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element
	 * @return the new stream
	 */
	DoubleStream mapToDouble(ToDoubleBiFunction<? super T, ? super U> mapper);

	/**
	 * Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. Each mapped stream is {@link java.util.stream.BaseStream#close() closed} after its contents have been
	 * placed into this stream. (If a mapped stream is {@code null} an empty stream is used, instead.)
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * The {@code flatMap()} operation has the effect of applying a one-to-many transformation to the elements of the stream, and then flattening the resulting elements into a new stream.
	 *
	 * <p>
	 * <b>Examples.</b>
	 *
	 * <p>
	 * If {@code orders} is a stream of purchase orders, and each purchase order contains a collection of line items, then the following produces a stream containing all the line items in all the orders:
	 * 
	 * <pre>
	 * {@code
	 *     orders.flatMap(order -> order.getLineItems().stream())...
	 * }
	 * </pre>
	 *
	 * <p>
	 * If {@code path} is the path to a file, then the following produces a stream of the {@code words} contained in that file:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	ObjectStream&lt;String&gt; lines = Files.lines(path, StandardCharsets.UTF_8);
	 * 	ObjectStream&lt;String&gt; words = lines.flatMap(line -&gt; ObjectStream.of(line.split(&quot; +&quot;)));
	 * }
	 * </pre>
	 * 
	 * The {@code mapper} function passed to {@code flatMap} splits a line, using a simple regular expression, into an array of words, and then creates a stream of words from that array.
	 *
	 * @param <R>
	 *            The element type of the new stream
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element which produces a stream of new values
	 * @return the new stream
	 */
	<R> Stream<R> flatMap(BiFunction<? super T, ? super U, ? extends Stream<? extends R>> mapper);

	/**
	 * Returns an {@code IntStream} consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. Each mapped stream is {@link java.util.stream.BaseStream#close() closed} after its contents
	 * have been placed into this stream. (If a mapped stream is {@code null} an empty stream is used, instead.)
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element which produces a stream of new values
	 * @return the new stream
	 * @see #flatMap(BiFunction)
	 */
	IntStream flatMapToInt(BiFunction<? super T, ? super U, ? extends IntStream> mapper);

	/**
	 * Returns an {@code LongStream} consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. Each mapped stream is {@link java.util.stream.BaseStream#close() closed} after its contents
	 * have been placed into this stream. (If a mapped stream is {@code null} an empty stream is used, instead.)
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element which produces a stream of new values
	 * @return the new stream
	 * @see #flatMap(BiFunction)
	 */
	LongStream flatMapToLong(BiFunction<? super T, ? super U, ? extends LongStream> mapper);

	/**
	 * Returns an {@code DoubleStream} consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. Each mapped stream is {@link java.util.stream.BaseStream#close() closed} after its
	 * contents have placed been into this stream. (If a mapped stream is {@code null} an empty stream is used, instead.)
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @param mapper
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function to apply to each element which produces a stream of new values
	 * @return the new stream
	 * @see #flatMap(BiFunction)
	 */
	DoubleStream flatMapToDouble(BiFunction<? super T, ? super U, ? extends DoubleStream> mapper);

	/**
	 * Returns a stream consisting of the distinct elements (according to {@link Object#equals(Object)}) of this stream.
	 *
	 * <p>
	 * For ordered streams, the selection of distinct elements is stable (for duplicated elements, the element appearing first in the encounter order is preserved.) For unordered streams, no stability guarantees are made.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
	 *
	 * Preserving stability for {@code distinct()} in parallel pipelines is relatively expensive (requires that the operation act as a full barrier, with substantial buffering overhead), and stability is often not needed. Using an unordered stream source (such as {@link #wrap(Stream)}) or removing
	 * the ordering constraint with {@link #unordered()} may result in significantly more efficient execution for {@code distinct()} in parallel pipelines, if the semantics of your situation permit. If consistency with encounter order is required, and you are experiencing poor performance or memory
	 * utilization with {@code distinct()} in parallel pipelines, switching to sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @return the new stream
	 */
	BiStream<T, U> distinct();

	/**
	 * Returns a stream consisting of the elements of this stream, sorted according to natural order. If the elements of this stream are not {@code Comparable}, a {@code java.lang.ClassCastException} may be thrown when the terminal operation is executed.
	 *
	 * <p>
	 * For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
	 *
	 * @return the new stream
	 */
	BiStream<T, U> sorted();

	/**
	 * Returns a stream consisting of the elements of this stream, sorted according to the provided {@code Comparator}.
	 *
	 * <p>
	 * For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
	 *
	 * @param comparator
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> {@code Comparator} to be used to compare stream elements
	 * @return the new stream
	 */
	BiStream<T, U> sorted(Comparator<? super BiValue<T, U>> comparator);

	/**
	 * Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.
	 *
	 * <p>
	 * This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * <p>
	 * For parallel stream pipelines, the action may be called at whatever time and in whatever thread the element is made available by the upstream operation. If the action modifies shared state, it is responsible for providing the required synchronization.
	 *
	 * This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline:
	 * 
	 * <pre>
	 * {@code
	 *     ObjectStream.of("one", "two", "three", "four")
	 *         .filter(e -> e.length() > 3)
	 *         .peek(e -> System.out.println("Filtered value: " + e))
	 *         .map(String::toUpperCase)
	 *         .peek(e -> System.out.println("Mapped value: " + e))
	 *         .collect(Collectors.toList());
	 * }
	 * </pre>
	 *
	 * @param action
	 *            a <a href="package-summary.html#NonInterference"> non-interfering</a> action to perform on the elements as they are consumed from the stream
	 * @return the new stream
	 */
	BiStream<T, U> peek(BiConsumer<? super T, ? super U> action);

	/**
	 * Returns a stream consisting of the elements of this stream, truncated to be no longer than {@code maxSize} in length.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting stateful intermediate operation</a>.
	 *
	 * While {@code limit()} is generally a cheap operation on sequential stream pipelines, it can be quite expensive on ordered parallel pipelines, especially for large values of {@code maxSize}, since {@code limit(n)} is constrained to return not just any <em>n</em> elements, but the
	 * <em>first n</em> elements in the encounter order. Using an unordered stream source (such as {@link #wrap(Stream)}) or removing the ordering constraint with {@link #unordered()} may result in significant speedups of {@code limit()} in parallel pipelines, if the semantics of your situation
	 * permit. If consistency with encounter order is required, and you are experiencing poor performance or memory utilization with {@code limit()} in parallel pipelines, switching to sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @param maxSize
	 *            the number of elements the stream should be limited to
	 * @return the new stream
	 * @throws IllegalArgumentException
	 *             if {@code maxSize} is negative
	 */
	BiStream<T, U> limit(long maxSize);

	/**
	 * Returns a stream consisting of the remaining elements of this stream after discarding the first {@code n} elements of the stream. If this stream contains fewer than {@code n} elements then an empty stream will be returned.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
	 *
	 * While {@code skip()} is generally a cheap operation on sequential stream pipelines, it can be quite expensive on ordered parallel pipelines, especially for large values of {@code n}, since {@code skip(n)} is constrained to skip not just any <em>n</em> elements, but the <em>first n</em>
	 * elements in the encounter order. Using an unordered stream source (such as {@link #wrap(Stream)}) or removing the ordering constraint with {@link #unordered()} may result in significant speedups of {@code skip()} in parallel pipelines, if the semantics of your situation permit. If consistency
	 * with encounter order is required, and you are experiencing poor performance or memory utilization with {@code skip()} in parallel pipelines, switching to sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @param n
	 *            the number of leading elements to skip
	 * @return the new stream
	 * @throws IllegalArgumentException
	 *             if {@code n} is negative
	 */
	BiStream<T, U> skip(long n);

	/**
	 * Performs an action for each element of this stream.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * <p>
	 * The behavior of this operation is explicitly nondeterministic. For parallel stream pipelines, this operation does <em>not</em> guarantee to respect the encounter order of the stream, as doing so would sacrifice the benefit of parallelism. For any given element, the action may be performed at
	 * whatever time and in whatever thread the library chooses. If the action accesses shared state, it is responsible for providing the required synchronization.
	 *
	 * @param action
	 *            a <a href="package-summary.html#NonInterference"> non-interfering</a> action to perform on the elements
	 */
	void forEach(BiConsumer<? super T, ? super U> action);

	/**
	 * Performs an action for each element of this stream, in the encounter order of the stream if the stream has a defined encounter order.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * <p>
	 * This operation processes the elements one at a time, in encounter order if one exists. Performing the action for one element <a href="../concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a> performing the action for subsequent elements, but for any given element, the
	 * action may be performed in whatever thread the library chooses.
	 *
	 * @param action
	 *            a <a href="package-summary.html#NonInterference"> non-interfering</a> action to perform on the elements
	 * @see #forEach(BiConsumer)
	 */
	void forEachOrdered(BiConsumer<? super T, ? super U> action);

	/**
	 * Returns an array containing the elements of this stream.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @return an array containing the elements of this stream
	 */
	Object[] toArray();

	/**
	 * Returns an array containing the elements of this stream, using the provided {@code generator} function to allocate the returned array, as well as any additional arrays that might be required for a partitioned execution or for resizing.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * The generator function takes an integer, which is the size of the desired array, and produces an array of the desired size. This can be concisely expressed with an array constructor reference:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	Person[] men = people.stream().filter(p -&gt; p.getGender() == MALE).toArray(Person[]::new);
	 * }
	 * </pre>
	 *
	 * @param <A>
	 *            the element type of the resulting array
	 * @param generator
	 *            a function which produces a new array of the desired type and the provided length
	 * @return an array containing the elements in this stream
	 * @throws ArrayStoreException
	 *             if the runtime type of the array returned from the array generator is not a supertype of the runtime type of every element in this stream
	 */
	<A> A[] toArray(IntFunction<A[]> generator);

	/**
	 * Performs a <a href="package-summary.html#Reduction">reduction</a> on the elements of this stream, using the provided identity value and an <a href="package-summary.html#Associativity">associative</a> accumulation function, and returns the reduced value. This is equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     T result = identity;
	 *     for (T element : this stream)
	 *         result = accumulator.apply(result, element)
	 *     return result;
	 * }
	 * </pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>
	 * The {@code identity} value must be an identity for the accumulator function. This means that for all {@code t}, {@code accumulator.apply(identity, t)} is equal to {@code t}. The {@code accumulator} function must be an <a href="package-summary.html#Associativity">associative</a> function.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * Sum, min, max, average, and string concatenation are all special cases of reduction. Summing a stream of numbers can be expressed as:
	 *
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	Integer sum = integers.reduce(0, (a, b) -&gt; a + b);
	 * }
	 * </pre>
	 *
	 * or:
	 *
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	Integer sum = integers.reduce(0, Integer::sum);
	 * }
	 * </pre>
	 *
	 * <p>
	 * While this may seem a more roundabout way to perform an aggregation compared to simply mutating a running total in a loop, reduction operations parallelize more gracefully, without needing additional synchronization and with greatly reduced risk of data races.
	 *
	 * @param identity
	 *            the identity value for the accumulating function
	 * @param accumulator
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for combining two values
	 * @return the result of the reduction
	 */
	BiValue<T, U> reduce(BiValue<T, U> identity, BinaryOperator<BiValue<T, U>> accumulator);

	/**
	 * Performs a <a href="package-summary.html#Reduction">reduction</a> on the elements of this stream, using an <a href="package-summary.html#Associativity">associative</a> accumulation function, and returns an {@code Optional} describing the reduced value, if any. This is equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     boolean foundAny = false;
	 *     T result = null;
	 *     for (T element : this stream) {
	 *         if (!foundAny) {
	 *             foundAny = true;
	 *             result = element;
	 *         }
	 *         else
	 *             result = accumulator.apply(result, element);
	 *     }
	 *     return foundAny ? Optional.of(result) : Optional.empty();
	 * }
	 * </pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>
	 * The {@code accumulator} function must be an <a href="package-summary.html#Associativity">associative</a> function.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @param accumulator
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for combining two values
	 * @return an {@link Optional} describing the result of the reduction
	 * @throws NullPointerException
	 *             if the result of the reduction is null
	 * @see #reduce(BiValue, BinaryOperator)
	 * @see #min(Comparator)
	 * @see #max(Comparator)
	 */
	Optional<BiValue<T, U>> reduce(BinaryOperator<BiValue<T, U>> accumulator);

	/**
	 * Performs a <a href="package-summary.html#Reduction">reduction</a> on the elements of this stream, using the provided identity, accumulation and combining functions. This is equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     U result = identity;
	 *     for (T element : this stream)
	 *         result = accumulator.apply(result, element)
	 *     return result;
	 * }
	 * </pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>
	 * The {@code identity} value must be an identity for the combiner function. This means that for all {@code u}, {@code combiner(identity, u)} is equal to {@code u}. Additionally, the {@code combiner} function must be compatible with the {@code accumulator} function; for all {@code u} and
	 * {@code t}, the following must hold:
	 * 
	 * <pre>
	 * {@code
	 *     combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
	 * }
	 * </pre>
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * Many reductions using this form can be represented more simply by an explicit combination of {@code map} and {@code reduce} operations. The {@code accumulator} function acts as a fused mapper and accumulator, which can sometimes be more efficient than separate mapping and reduction, such as
	 * when knowing the previously reduced value allows you to avoid some computation.
	 *
	 * @param <R>
	 *            The type of the result
	 * @param identity
	 *            the identity value for the combiner function
	 * @param accumulator
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for incorporating an additional element into a result
	 * @param combiner
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for combining two values, which must be compatible with the accumulator function
	 * @return the result of the reduction
	 * @see #reduce(BinaryOperator)
	 * @see #reduce(BiValue, BinaryOperator)
	 */
	<R> R reduce(R identity, BiFunction<R, ? super BiValue<T, U>, R> accumulator, BinaryOperator<R> combiner);

	/**
	 * Performs a <a href="package-summary.html#MutableReduction">mutable reduction</a> operation on the elements of this stream. A mutable reduction is one in which the reduced value is a mutable result container, such as an {@code ArrayList}, and elements are incorporated by updating the state of
	 * the result rather than by replacing the result. This produces a result equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     R result = supplier.get();
	 *     for (T element : this stream)
	 *         accumulator.accept(result, element);
	 *     return result;
	 * }
	 * </pre>
	 *
	 * <p>
	 * Like {@link #reduce(BiValue, BinaryOperator)}, {@code collect} operations can be parallelized without requiring additional synchronization.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * There are many existing classes in the JDK whose signatures are well-suited for use with method references as arguments to {@code collect()}. For example, the following will accumulate strings into an {@code ArrayList}:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	List&lt;String&gt; asList = stringStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	 * }
	 * </pre>
	 *
	 * <p>
	 * The following will take a stream of strings and concatenates them into a single string:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	String concat = stringStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	 * }
	 * </pre>
	 *
	 * @param <R>
	 *            type of the result
	 * @param supplier
	 *            a function that creates a new result container. For a parallel execution, this function may be called multiple times and must return a fresh value each time.
	 * @param accumulator
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for incorporating an additional element into a result
	 * @param combiner
	 *            an <a href="package-summary.html#Associativity">associative</a>, <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> function for combining two values, which must be compatible with the accumulator function
	 * @return the result of the reduction
	 */
	<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super BiValue<T, U>> accumulator, BiConsumer<R, R> combiner);

	/**
	 * Performs a <a href="package-summary.html#MutableReduction">mutable reduction</a> operation on the elements of this stream using a {@code Collector}. A {@code Collector} encapsulates the functions used as arguments to {@link #collect(Supplier, BiConsumer, BiConsumer)}, allowing for reuse of
	 * collection strategies and composition of collect operations such as multiple-level grouping or partitioning.
	 *
	 * <p>
	 * If the stream is parallel, and the {@code Collector} is {@link Collector.Characteristics#CONCURRENT concurrent}, and either the stream is unordered or the collector is {@link Collector.Characteristics#UNORDERED unordered}, then a concurrent reduction will be performed (see {@link Collector}
	 * for details on concurrent reduction.)
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * <p>
	 * When executed in parallel, multiple intermediate results may be instantiated, populated, and merged so as to maintain isolation of mutable data structures. Therefore, even when executed in parallel with non-thread-safe data structures (such as {@code ArrayList}), no additional synchronization
	 * is needed for a parallel reduction.
	 *
	 * The following will accumulate strings into an ArrayList:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	List&lt;String&gt; asList = stringStream.collect(Collectors.toList());
	 * }
	 * </pre>
	 *
	 * <p>
	 * The following will classify {@code Person} objects by city:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	Map&lt;String, List&lt;Person&gt;&gt; peopleByCity = personStream.collect(Collectors.groupingBy(Person::getCity));
	 * }
	 * </pre>
	 *
	 * <p>
	 * The following will classify {@code Person} objects by state and city, cascading two {@code Collector}s together:
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	Map&lt;String, Map&lt;String, List&lt;Person&gt;&gt;&gt; peopleByStateAndCity = personStream.collect(Collectors.groupingBy(Person::getState, Collectors.groupingBy(Person::getCity)));
	 * }
	 * </pre>
	 *
	 * @param <R>
	 *            the type of the result
	 * @param <A>
	 *            the intermediate accumulation type of the {@code Collector}
	 * @param collector
	 *            the {@code Collector} describing the reduction
	 * @return the result of the reduction
	 * @see #collect(Supplier, BiConsumer, BiConsumer)
	 * @see Collectors
	 */
	<R, A> R collect(Collector<? super BiValue<T, U>, A, R> collector);

	/**
	 * Returns the minimum element of this stream according to the provided {@code Comparator}. This is a special case of a <a href="package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @param comparator
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> {@code Comparator} to compare elements of this stream
	 * @return an {@code Optional} describing the minimum element of this stream, or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException
	 *             if the minimum element is null
	 */
	Optional<BiValue<T, U>> min(Comparator<? super BiValue<T, U>> comparator);

	/**
	 * Returns the maximum element of this stream according to the provided {@code Comparator}. This is a special case of a <a href="package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @param comparator
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> {@code Comparator} to compare elements of this stream
	 * @return an {@code Optional} describing the maximum element of this stream, or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException
	 *             if the maximum element is null
	 */
	Optional<BiValue<T, U>> max(Comparator<? super BiValue<T, U>> comparator);

	/**
	 * Returns the count of elements in this stream. This is a special case of a <a href="package-summary.html#Reduction">reduction</a> and is equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     return mapToLong(e -> 1L).sum();
	 * }
	 * </pre>
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @return the count of elements in this stream
	 */
	long count();

	/**
	 * Returns whether any elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then {@code false} is returned and the predicate is not evaluated.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
	 *
	 * This method evaluates the <em>existential quantification</em> of the predicate over the elements of the stream (for some x P(x)).
	 *
	 * @param predicate
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of this stream
	 * @return {@code true} if any elements of the stream match the provided predicate, otherwise {@code false}
	 */
	boolean anyMatch(BiPredicate<? super T, ? super U> predicate);

	/**
	 * Returns whether all elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then {@code true} is returned and the predicate is not evaluated.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
	 *
	 * This method evaluates the <em>universal quantification</em> of the predicate over the elements of the stream (for all x P(x)). If the stream is empty, the quantification is said to be <em>vacuously
	 * satisfied</em> and is always {@code true} (regardless of P(x)).
	 *
	 * @param predicate
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of this stream
	 * @return {@code true} if either all elements of the stream match the provided predicate or the stream is empty, otherwise {@code false}
	 */
	boolean allMatch(BiPredicate<? super T, ? super U> predicate);

	/**
	 * Returns whether no elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then {@code true} is returned and the predicate is not evaluated.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
	 *
	 * This method evaluates the <em>universal quantification</em> of the negated predicate over the elements of the stream (for all x ~P(x)). If the stream is empty, the quantification is said to be vacuously satisfied and is always {@code true}, regardless of P(x).
	 *
	 * @param predicate
	 *            a <a href="package-summary.html#NonInterference">non-interfering</a>, <a href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of this stream
	 * @return {@code true} if either no elements of the stream match the provided predicate or the stream is empty, otherwise {@code false}
	 */
	boolean noneMatch(BiPredicate<? super T, ? super U> predicate);

	/**
	 * Returns an {@link Optional} describing the first element of this stream, or an empty {@code Optional} if the stream is empty. If the stream has no encounter order, then any element may be returned.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
	 *
	 * @return an {@code Optional} describing the first element of this stream, or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException
	 *             if the element selected is null
	 */
	Optional<BiValue<T, U>> findFirst();

	/**
	 * Returns an {@link Optional} describing some element of the stream, or an empty {@code Optional} if the stream is empty.
	 *
	 * <p>
	 * This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
	 *
	 * <p>
	 * The behavior of this operation is explicitly nondeterministic; it is free to select any element in the stream. This is to allow for maximal performance in parallel operations; the cost is that multiple invocations on the same source may not return the same result. (If a stable result is
	 * desired, use {@link #findFirst()} instead.)
	 *
	 * @return an {@code Optional} describing some element of this stream, or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException
	 *             if the element selected is null
	 * @see #findFirst()
	 */
	Optional<BiValue<T, U>> findAny();

	/**
	 * Create an {@link BiStream} with the two Arrays , this BiStream contains all elements of the two array
	 *
	 *
	 * @return an {@code BiStream} with the elements of the two arrays
	 *
	 * @see #wrap(Stream, Stream)
	 */
	static <T, U> BiStream<T, U> wrap(T[] t, U[] u) {
		return wrap(Arrays.stream(t), Arrays.stream(u));
	}

	/**
	 * Create an {@link BiStream} with the two Collections , this BiStream contains all elements of the two streams
	 *
	 *
	 * @return an {@code BiStream} with the elements of the two collections
	 *
	 * @see #wrap(Stream , Stream)
	 */
	static <T, U> BiStream<T, U> wrap(Collection<T> t, Collection<U> u) {
		return wrap(t.stream(), u.stream());
	}

	/**
	 * Create an {@link BiStream} with the two Streams , this BiStream contains all elements of the two streams
	 *
	 *
	 * @return an {@code BiStream} with the elements of the two arrays
	 *
	 * @see #wrap(Spliterator , Spliterator)
	 *
	 */
	static <T, U> BiStream<T, U> wrap(Stream<T> t, Stream<U> u) {
		return wrap(t.spliterator(), u.spliterator());
	}

	/**
	 * Create an {@link BiStream} with the two Streams , this BiStream contains all elements of the two streams
	 *
	 *
	 * @return an {@code BiStream} with the elements of the two arrays
	 *
	 * @see #wrap(Stream)
	 */
	static <T, U> BiStream<T, U> wrap(Spliterator<T> t, Spliterator<U> u) {
		return wrap(StreamSupport.stream(new BiSpliterator<>(t, u), false));
	}

	/**
	 * Returns an {@link BiStream} with the stream
	 *
	 *
	 * @return an {@code BiStream} with the stream
	 *
	 * @see #wrap(Object[], Object[])
	 */
	static <T, U> BiStream<T, U> wrap(Stream<BiStream.BiValue<T, U>> stream) {
		return new BiStreamImpl<>(stream);
	}

}