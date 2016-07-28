package fr.aresrpg.commons.domain.util;

import java.util.Arrays;
import java.util.List;

/**
 * A util class to manipulate arrays
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class ArrayUtils {
	private ArrayUtils(){throw new IllegalConstructionException();}

	/**
	 * Convert this byte array to a Wrapper byte array
	 * @param bytes the byte array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Byte[] toObject(byte[] bytes){
		Byte[] oBytes = new Byte[bytes.length];
		for(int i = 0 ; i < bytes.length ; i++)
			oBytes[i] = bytes[i];
		return oBytes;
	}

	/**
	 * Convert this shorts array to a Wrapper short array
	 * @param shorts the shorts array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Short[] toObject(short[] shorts){
		Short[] oShorts = new Short[shorts.length];
		for(int i = 0 ; i < shorts.length ; i++)
			oShorts[i] = shorts[i];
		return oShorts;
	}

	/**
	 * Convert this byte array to a Wrapper int array
	 * @param ints the byte array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Integer[] toObject(int[] ints){
		Integer[] oInts = new Integer[ints.length];
		for(int i = 0 ; i < ints.length ; i++)
			oInts[i] = ints[i];
		return oInts;
	}

	/**
	 * Convert this long array to a Wrapper long array
	 * @param longs the long array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Long[] toObject(long[] longs){
		Long[] oLongs = new Long[longs.length];
		for(int i = 0 ; i < longs.length ; i++)
			oLongs[i] = longs[i];
		return oLongs;
	}

	/**
	 * Convert this float array to a Wrapper float array
	 * @param floats the float array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Float[] toObject(float[] floats){
		Float[] oFloats = new Float[floats.length];
		for(int i = 0 ; i < floats.length ; i++)
			oFloats[i] = floats[i];
		return oFloats;
	}

	/**
	 * Convert this float array to a Wrapper double array
	 * @param doubles the double array to convert
	 * @return a Primitive Wrapper Array
	 */
	public static Double[] toObject(double[] doubles){
		Double[] oDoubles = new Double[doubles.length];
		for(int i = 0 ; i < doubles.length ; i++)
			oDoubles[i] = doubles[i];
		return oDoubles;
	}

	/**
	 * Convert this byte array to a byte List
	 *
	 * @param bytes the byte array to convert
	 * @return a byte List
	 */
	public static List<Byte> asList(byte[] bytes){
		return Arrays.asList(toObject(bytes));
	}

	/**
	 * Convert this short array to a short List
	 *
	 * @param shorts the byte array to convert
	 * @return a short List
	 */
	public static List<Short> asList(short[] shorts){
		return Arrays.asList(toObject(shorts));
	}

	/**
	 * Convert this short array to a int List
	 *
	 * @param ints the int array to convert
	 * @return a int List
	 */
	public static List<Integer> asList(int[] ints){
		return Arrays.asList(toObject(ints));
	}

	/**
	 * Convert this long array to a long List
	 *
	 * @param longs the long array to convert
	 * @return a long List
	 */
	public static List<Long> asList(long[] longs){
		return Arrays.asList(toObject(longs));
	}

	/**
	 * Convert this float array to a float List
	 *
	 * @param floats the float array to convert
	 * @return a float List
	 */
	public static List<Float> asList(float[] floats){
		return Arrays.asList(toObject(floats));
	}

	/**
	 * Convert this doubles array to a double List
	 *
	 * @param doubles the byte array to convert
	 * @return a double List
	 */
	public static List<Double> asList(double[] doubles){
		return Arrays.asList(toObject(doubles));
	}
}
