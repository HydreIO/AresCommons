package fr.aresrpg.commons.domain.util;

/**
 * A pair to contain the first value , or the second , or the twice
 * @param <F> First type contained in this pair
 * @param <S> Second type contained in this pair
 * @see Tuple
 * @see Pair
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class Or<F , S> extends Pair<F , S>{

	/**
	 * {@inheritDoc}
	 */
	public Or(F first, S second) {
		super(first, second);
	}

	/**
	 * Get if is the first value
	 * @return if is the first value
	 */
	public boolean isFirst(){
		return first != null;
	}

	/**
	 * Get if is the second value
	 * @return if is the second value
	 */
	public boolean isSecond(){
		return second != null;
	}

	/**
	 * Get if is the first and the second value
	 * @return if is the first and the second value
	 */
	public boolean isTwice(){
		return isFirst() && isSecond();
	}

	/**
	 * Create a or containing the first value
	 * @param first the first value
	 * @param <T> the first value type
	 * @param <I> the second value type
	 * @return a or containing the first value
	 */
	public static <T , I> Or<T , I> first(T first){
		return new Or<>(first , null);
	}

	/**
	 * Create a or containing the second value
	 * @param second the second value
	 * @param <T> the first value type
	 * @param <I> the second value type
	 * @return a or containing the second value
	 */
	public static <T , I> Or<I , T> second(T second){
		return new Or<>(null , second);
	}

	@Override
	public String toString() {
		return (isFirst() ? first.toString() : "") + (isSecond() ? second.toString() : "");
	}
}
