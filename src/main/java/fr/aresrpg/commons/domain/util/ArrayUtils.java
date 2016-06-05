package fr.aresrpg.commons.domain.util;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
	private ArrayUtils(){}

	public static Byte[] toObject(byte[] bytes){
		Byte[] oBytes = new Byte[bytes.length];
		for(int i = 0 ; i < bytes.length ; i++)
			oBytes[i] = bytes[i];
		return oBytes;
	}

	public static Short[] toObject(short[] shorts){
		Short[] oShorts = new Short[shorts.length];
		for(int i = 0 ; i < shorts.length ; i++)
			oShorts[i] = shorts[i];
		return oShorts;
	}
	public static Integer[] toObject(int[] ints){
		Integer[] oInts = new Integer[ints.length];
		for(int i = 0 ; i < ints.length ; i++)
			oInts[i] = ints[i];
		return oInts;
	}
	public static Long[] toObject(long[] longs){
		Long[] oLongs = new Long[longs.length];
		for(int i = 0 ; i < longs.length ; i++)
			oLongs[i] = longs[i];
		return oLongs;
	}
	public static Float[] toObject(float[] floats){
		Float[] oFloats = new Float[floats.length];
		for(int i = 0 ; i < floats.length ; i++)
			oFloats[i] = floats[i];
		return oFloats;
	}
	public static Double[] toObject(double[] doubles){
		Double[] oDoubles = new Double[doubles.length];
		for(int i = 0 ; i < doubles.length ; i++)
			oDoubles[i] = doubles[i];
		return oDoubles;
	}

	public static List<Byte> asList(byte[] bytes){
		return Arrays.asList(toObject(bytes));
	}

	public static List<Short> asList(short[] shorts){
		return Arrays.asList(toObject(shorts));
	}

	public static List<Integer> asList(int[] ints){
		return Arrays.asList(toObject(ints));
	}

	public static List<Long> asList(long[] longs){
		return Arrays.asList(toObject(longs));
	}

	public static List<Float> asList(float[] floats){
		return Arrays.asList(toObject(floats));
	}

	public static List<Double> asList(double[] doubles){
		return Arrays.asList(toObject(doubles));
	}
}
