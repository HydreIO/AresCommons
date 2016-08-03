package fr.aresrpg.commons.domain.util;

import java.util.*;

/**
 * A util class to manipulate arrays
 * 
 * @author Duarte David <deltaduartedavid @ gmail.com>
 * @author MrSceat <sceat@aresrpg.fr>
 */
public class ArrayUtils {
	private ArrayUtils() {
		throw new IllegalConstructionException();
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(byte[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(short[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(int[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(long[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(float[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(double[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains elements
	 * 
	 * @param array
	 *            the array
	 * @return true if the array contains some elements
	 */
	public static <T> boolean notEmpty(char[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if the passed array contains @NonNull elements
	 * 
	 * @param array
	 *            the array
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
	 * @return true if the array contains any element
	 */
	public static <T> boolean notEmpty(T[] array, boolean allowNull) {
		Objects.requireNonNull(array);
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
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(byte[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(short[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(int[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(long[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(float[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(double[] array) {
		return array.length == 0;
	}

	/**
	 * Check if the passed array is empty or not
	 * 
	 * @param array
	 *            the array
	 * @return true if empty
	 */
	public static <T> boolean isEmpty(char[] array) {
		return array.length == 0;
	}

	/**
	 * Convert this byte array to a Wrapper byte array
	 * 
	 * @param bytes
	 *            the byte array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Byte[] toObject(byte[] bytes) {
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
	public static List<Byte> asList(byte[] bytes) {
		return Arrays.asList(toObject(bytes));
	}

	/**
	 * Convert this short array to a short List
	 *
	 * @param shorts
	 *            the short array to convert
	 * @return a short List
	 */
	public static List<Short> asList(short[] shorts) {
		return Arrays.asList(toObject(shorts));
	}

	/**
	 * Convert this short array to a int List
	 *
	 * @param ints
	 *            the int array to convert
	 * @return a int List
	 */
	public static List<Integer> asList(int[] ints) {
		return Arrays.asList(toObject(ints));
	}

	/**
	 * Convert this long array to a long List
	 *
	 * @param longs
	 *            the long array to convert
	 * @return a long List
	 */
	public static List<Long> asList(long[] longs) {
		return Arrays.asList(toObject(longs));
	}

	/**
	 * Convert this float array to a float List
	 *
	 * @param floats
	 *            the float array to convert
	 * @return a float List
	 */
	public static List<Float> asList(float[] floats) {
		return Arrays.asList(toObject(floats));
	}

	/**
	 * Convert this doubles array to a double List
	 *
	 * @param doubles
	 *            the double array to convert
	 * @return a double List
	 */
	public static List<Double> asList(double[] doubles) {
		return Arrays.asList(toObject(doubles));
	}

	/**
	 * Convert this char array to a character List
	 *
	 * @param chars
	 *            the char array to convert
	 * @return a character List
	 */
	public static List<Character> asList(char[] chars) {
		return Arrays.asList(toObject(chars));
	}
}
