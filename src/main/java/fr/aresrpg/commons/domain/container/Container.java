package fr.aresrpg.commons.domain.container;

import fr.aresrpg.commons.domain.container.empty.EmptyContainer;
import fr.aresrpg.commons.domain.container.immutable.ImmutableContainer;
import fr.aresrpg.commons.domain.container.singleton.SingletonContainer;
import fr.aresrpg.commons.domain.Sized;
import fr.aresrpg.commons.domain.Value;
import fr.aresrpg.commons.domain.condition.functional.suplier.Supplier;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Container<E> extends Sized<E> , Value<E>{

	default boolean remove(E o){
		return unsafeRemove(o);
	}

	boolean unsafeRemove(Object o);

	default boolean removeAll(Iterable<E> o){
		return unsafeRemove(o);
	}

	boolean unsafeRemoveAll(Iterable o);

	default boolean add(E o){
		return unsafeAdd(o);
	}

	boolean unsafeAdd(Object o);

	default boolean addAll(Iterable<E> o){
		return unsafeAddAll(o);
	}

	boolean unsafeAddAll(Iterable o);

	default boolean contains(E o){
		return unsafeContains(o);
	}

	boolean unsafeContains(Object o);

	default boolean containsAll(Iterable<E> o){
		return unsafeContainsAll(o);
	}

	@SuppressWarnings("unchecked")
	default boolean unsafeContainsAll(Iterable o) {
		Iterator it = o.iterator();
		while(it.hasNext())
			if(!unsafeContains(it.next()))
				return false;
		return true;
	}

	default int containsCount(Iterable<E> o){
		return unsafeContainsCount(o);
	}

	@SuppressWarnings("unchecked")
	default int unsafeContainsCount(Iterable o) {
		Iterator it = o.iterator();
		int i = 0;
		while(it.hasNext())
			if(unsafeContains(it.next()))
				i++;
		return i;
	}

	void clear();

	default Stream<E> stream() {
		return StreamSupport.stream(spliterator() , false);
	}

	default Stream<E> parallelStream() {
		return StreamSupport.stream(spliterator() , true);
	}

	default java.util.Collection<E> toJava() {
		return (JavaAdapter<E>) () -> this;
	}

	@Override
	default boolean isEmpty(){
		return size() == 0;
	}

	boolean isImmutable();

	boolean isConcurrent();

	default Container<E> toImmutable(){
		return isImmutable() ? this : immutable(toArray());
	}

	default Collector<E , ? , Container<E>> collector(){
		return Collector.of(() -> this , Container::add, Container::combine,
				isConcurrent() ?
						new Collector.Characteristics[]{Collector.Characteristics.CONCURRENT} :
						new Collector.Characteristics[0]);
	}

	@FunctionalInterface
	interface JavaAdapter<E> extends Collection<E> , Supplier<Container<E>> {
		@Override
		default int size() {
			return (int) get().size();
		}

		@Override
		default boolean isEmpty() {
			return get().isEmpty();
		}

		@Override
		default boolean contains(Object o) {
			return get().unsafeContains(o);
		}

		@Override
		default Iterator<E> iterator() {
			return get().iterator();
		}

		@Override
		default Object[] toArray() {
			return get().toArray();
		}

		@Override
		default <T> T[] toArray(T[] a) {
			return get().toArray(a);
		}

		@Override
		default boolean add(E e) {
			return get().add(e);
		}

		@Override
		default boolean remove(Object o) {
			return get().unsafeRemove(o);
		}

		@Override
		default boolean containsAll(Collection<?> c) {
			return get().unsafeContainsAll(c);
		}

		@Override
		default boolean addAll(Collection<? extends E> c) {
			return get().unsafeAddAll(c);
		}

		@Override
		default boolean removeAll(Collection<?> c) {
			return get().unsafeRemoveAll(c);
		}

		@Override
		default boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		default void clear() {
			get().clear();
		}
	}

	static <T> Container<T> singleton(T value){
		return (SingletonContainer<T>) () -> value;
	}

	@SafeVarargs
	static <T> Container<T> immutable(T... values){
		return (ImmutableContainer<T>) () -> values;
	}

	@SuppressWarnings("unchecked")
	static <T> Container<T> empty(){
		return EmptyContainer.INSTANCE;
	}

	static <T> Container<T> combine(Container<T> first , Container<T> second){
		first.addAll(second);
		return first;
	}
}
