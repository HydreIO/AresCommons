package fr.aresrpg.commons.domain.util;

/**
 * A pair to contain the first value , the second, or twice
 * 
 * @param <F>
 *            First type contained in this pair
 * @param <S>
 *            Second type contained in this pair
 * @see Tuple
 * @see Pair
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Or<F, S> extends Pair<F, S> {

	/**
	 * {@inheritDoc}
	 */
	public Or(F first, S second) {
		super(first, second);
	}

	/**
	 * Whether or not the {@linkplain Or} contains the first value
	 * 
	 * @return if is the first value
	 */
	public boolean containFirst() {
		return first != null;
	}

	/**
	 * Whether or not the {@linkplain Or} contains the second value
	 * 
	 * @return if is the second value
	 */
	public boolean containSecond() {
		return second != null;
	}

	/**
	 * @return true when the two values are present
	 */
	public boolean containTwice() {
		return containFirst() && containSecond();
	}

	/**
	 * Create a or containing the first value
	 * 
	 * @param first
	 *            the first value
	 * @param <T>
	 *            the first value type
	 * @param <I>
	 *            the second value type
	 * @return a or containing the first value
	 */
	public static <T, I> Or<T, I> first(T first) {
		return new Or<>(first, null);
	}

	/**
	 * Create a or containing the second value
	 * 
	 * @param second
	 *            the second value
	 * @param <T>
	 *            the first value type
	 * @param <I>
	 *            the second value type
	 * @return a or containing the second value
	 */
	public static <T, I> Or<T, I> second(I second) {
		return new Or<>(null, second);
	}

	@Override
	public String toString() {
		return (containFirst() ? first.toString() : "") + (containSecond() ? containFirst() ? " | " + second.toString() : second.toString() : "");
	}
}
