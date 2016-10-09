package fr.aresrpg.commons.domain.util;

import fr.aresrpg.commons.domain.condition.Option;
import fr.aresrpg.commons.domain.functional.function.Function;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A util class to manipulate arrays
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class ArrayUtils {
	private ArrayUtils() {
		throw new IllegalConstructionException();
	}

	/**
	 * Check if an object is inside an array
	 * 
	 * @param value
	 *            the object
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of the array
	 * @return true if the object is equal to an object into the array
	 */
	public static <T> boolean contains(T value, T... array) {
		if (array == null) return false;
		for (T t : array)
			if (t.equals(value)) return true;
		return false;
	}

	/**
	 * Check if a boolean is inside an array
	 * 
	 * @param b
	 *            the boolean
	 * @param array
	 *            the array
	 * @return true if the boolean is inside the array
	 */
	public static boolean contains(boolean b, boolean... array) {
		if (array == null) return false;
		for (boolean bt : array)
			if (bt == b) return true;
		return false;
	}

	/**
	 * Check if a byte is inside an array
	 * 
	 * @param b
	 *            the byte
	 * @param array
	 *            the array
	 * @return true if the byte is inside the array
	 */
	public static boolean contains(byte b, byte... array) {
		if (array == null) return false;
		for (byte bt : array)
			if (b == bt) return true;
		return false;
	}

	/**
	 * Check if a short is inside an array
	 * 
	 * @param b
	 *            the short
	 * @param array
	 *            the array
	 * @return true if the short is inside the array
	 */
	public static boolean contains(short b, short... array) {
		if (array == null) return false;
		for (short bt : array)
			if (b == bt) return true;
		return false;
	}

	/**
	 * Check if an int is inside an array
	 * 
	 * @param b
	 *            the int
	 * @param array
	 *            the array
	 * @return true if the int is inside the array
	 */
	public static boolean contains(int b, int... array) {
		if (array == null) return false;
		for (int bt : array)
			if (b == bt) return true;
		return false;
	}

	/**
	 * Check if a long is inside an array
	 * 
	 * @param b
	 *            the long
	 * @param array
	 *            the array
	 * @return true if the long is inside the array
	 */
	public static boolean contains(long b, long... array) {
		if (array == null) return false;
		for (long bt : array)
			if (b == bt) return true;
		return false;
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(byte[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(short[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(int[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(long[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(float[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(double[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static boolean notEmpty(char[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains @NonNull elements
	 * 
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(T[] array) {
		return notEmpty(array, false);
	}

	/**
	 * Check if the passed array contains any elements, if @allowNull then we consider that a null element is a valid element
	 * 
	 * @param array
	 *            the array
	 * @param allowNull
	 *            if null elements are allowed (alias= considerNullLikeAnElement)
	 * @param <T>
	 *            the type of the array
	 * @return true if the array contains any element
	 */
	public static <T> boolean notEmpty(T[] array, boolean allowNull) {
		if (array == null) return false;
		if (allowNull) return !isEmpty(array); // fail fast
		for (T t : array)
			if (t != null) return true;
		return false;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static boolean isEmpty(char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Convert this byte array to a Wrapper byte array
	 * 
	 * @param bytes
	 *            the byte array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Byte[] toObject(byte[] bytes) {
		if (bytes == null) return new Byte[0];
		Byte[] oBytes = new Byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			oBytes[i] = bytes[i];
		return oBytes;
	}

	/**
	 * Convert this shorts array to a Wrapper short array
	 * 
	 * @param shorts
	 *            the shorts array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Short[] toObject(short[] shorts) {
		if (shorts == null) return new Short[0];
		Short[] oShorts = new Short[shorts.length];
		for (int i = 0; i < shorts.length; i++)
			oShorts[i] = shorts[i];
		return oShorts;
	}

	/**
	 * Convert this int array to a Wrapper int array
	 * 
	 * @param ints
	 *            the int array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Integer[] toObject(int[] ints) {
		if (ints == null) return new Integer[0];
		Integer[] oInts = new Integer[ints.length];
		for (int i = 0; i < ints.length; i++)
			oInts[i] = ints[i];
		return oInts;
	}

	/**
	 * Convert this long array to a Wrapper long array
	 * 
	 * @param longs
	 *            the long array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Long[] toObject(long[] longs) {
		if (longs == null) return new Long[0];
		Long[] oLongs = new Long[longs.length];
		for (int i = 0; i < longs.length; i++)
			oLongs[i] = longs[i];
		return oLongs;
	}

	/**
	 * Convert this float array to a Wrapper float array
	 * 
	 * @param floats
	 *            the float array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Float[] toObject(float[] floats) {
		if (floats == null) return new Float[0];
		Float[] oFloats = new Float[floats.length];
		for (int i = 0; i < floats.length; i++)
			oFloats[i] = floats[i];
		return oFloats;
	}

	/**
	 * Convert this double array to a Wrapper double array
	 * 
	 * @param doubles
	 *            the double array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Double[] toObject(double[] doubles) {
		if (doubles == null) return new Double[0];
		Double[] oDoubles = new Double[doubles.length];
		for (int i = 0; i < doubles.length; i++)
			oDoubles[i] = doubles[i];
		return oDoubles;
	}

	/**
	 * Convert this char array to a Wrapper character array
	 * 
	 * @param chars
	 *            the char array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Character[] toObject(char[] chars) {
		if (chars == null) return new Character[0];
		Character[] oChar = new Character[chars.length];
		for (int i = 0; i < chars.length; i++)
			oChar[i] = chars[i];
		return oChar;
	}

	/**
	 * Convert this byte array to a byte List
	 *
	 * @param bytes
	 *            the byte array to convert
	 * @return a byte List
	 */
	public static List<Byte> asList(byte... bytes) {
		if (bytes == null) return new ArrayList<>();
		return Arrays.asList(toObject(bytes));
	}

	/**
	 * Convert this byte array to a Byte set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Byte> asSet(byte... values) {
		Set<Byte> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this short array to a short List
	 *
	 * @param shorts
	 *            the short array to convert
	 * @return a short List
	 */
	public static List<Short> asList(short... shorts) {
		if (shorts == null) return new ArrayList<>();
		return Arrays.asList(toObject(shorts));
	}

	/**
	 * Convert this short array to a Short set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Short> asSet(short... values) {
		Set<Short> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this short array to a int List
	 *
	 * @param ints
	 *            the int array to convert
	 * @return a int List
	 */
	public static List<Integer> asList(int... ints) {
		if (ints == null) return new ArrayList<>();
		return Arrays.asList(toObject(ints));
	}

	/**
	 * Convert this int array to a Integer set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Integer> asSet(int... values) {
		Set<Integer> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this long array to a long List
	 *
	 * @param longs
	 *            the long array to convert
	 * @return a long List
	 */
	public static List<Long> asList(long... longs) {
		if (longs == null) return new ArrayList<>();
		return Arrays.asList(toObject(longs));
	}

	/**
	 * Convert this long array to a Long set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Long> asSet(long... values) {
		Set<Long> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this float array to a float List
	 *
	 * @param floats
	 *            the float array to convert
	 * @return a float List
	 */
	public static List<Float> asList(float... floats) {
		if (floats == null) return new ArrayList<>();
		return Arrays.asList(toObject(floats));
	}

	/**
	 * Convert this float array to a Float set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Float> asSet(float... values) {
		Set<Float> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this doubles array to a double List
	 *
	 * @param doubles
	 *            the double array to convert
	 * @return a double List
	 */
	public static List<Double> asList(double... doubles) {
		if (doubles == null) return new ArrayList<>();
		return Arrays.asList(toObject(doubles));
	}

	/**
	 * Convert this double array to a Double set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Double> asSet(double... values) {
		Set<Double> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this char array to a character List
	 *
	 * @param chars
	 *            the char array to convert
	 * @return a character List
	 */
	public static List<Character> asList(char... chars) {
		if (chars == null) return new ArrayList<>();
		return Arrays.asList(toObject(chars));
	}

	/**
	 * Convert this char array to a Character set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static Set<Character> asSet(char... values) {
		Set<Character> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Convert this T array to a Set
	 * 
	 * @param values
	 *            the array
	 * @return a set containing all values
	 */
	public static <T> Set<T> asSet(T... values) {
		Set<T> set = new HashSet<>();
		if (values == null) return set;
		for (int i = 0; i < values.length; i++)
			set.add(values[i]);
		return set;
	}

	/**
	 * Get the last value contained in a array
	 * 
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of the array
	 * @return the last value or null if the array is empty
	 */
	public static <T> T lastValue(T[] array) {
		Objects.requireNonNull(array);
		if (isEmpty(array)) return null;
		return array[array.length - 1];
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Byte> lastValue(byte[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Byte.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Short> lastValue(short[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Short.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Long> lastValue(long[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Long.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Integer> lastValue(int[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Integer.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Float> lastValue(float[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Float.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Double> lastValue(double[] array) {
		if (isEmpty(array)) return Option.none();
		return Option.some(Double.valueOf(array[array.length - 1]));
	}

	/**
	 * Get the last value contained in the array
	 * 
	 * @param array
	 *            the array
	 * @return the last value or an empty option if the array is empty
	 */
	public static Option<Character> lastValue(char[] array) {
		if (isEmpty(array)) return Option.none();

		return Option.some(Character.valueOf(array[array.length - 1]));
	}

	/**
	 * <p>
	 * Join all values from an array with a mapper
	 * </p>
	 * Exemple of use:
	 * 
	 * <pre>
	 * ArrayUtils.toString(Object::toString,4,5,6);
	 * 
	 * Output: [4,5,6]
	 * </pre>
	 * 
	 * @param mapper
	 *            the mapper
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of the array
	 * @return the content of the array depending to the mapper
	 */
	public static <T> String toSring(Function<T, String> mapper, T... array) {
		return Arrays.stream(array).map(mapper::apply).collect(Collectors.joining(",", "[", "]"));
	}

	/**
	 * Merge two arrays
	 * 
	 * @param first
	 *            the first array
	 * @param second
	 *            the second array
	 * @param <T>
	 *            the type of the array
	 * @return an array which contains all elements from the two passed arrays
	 */
	public static <T> T[] concat(T[] first, T[] second) {
		if (first == null) return second;
		if (second == null) return first;
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	/**
	 * Merge all arrays
	 * 
	 * @param first
	 *            the first array
	 * @param others
	 *            the others arrays
	 * @param <T>
	 *            the type of the array
	 * @return an array which contains all elements from first and others arrays
	 */
	public static <T> T[] concat(T[] first, T[]... others) {
		if (others == null) return first;
		T[] result = first;
		for (T[] t : others)
			result = concat(first, t);
		return result;
	}
}
