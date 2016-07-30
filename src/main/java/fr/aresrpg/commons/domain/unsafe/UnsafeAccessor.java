package fr.aresrpg.commons.domain.unsafe;

import java.lang.reflect.Field;

import fr.aresrpg.commons.domain.util.IllegalConstructionException;
import sun.misc.Unsafe;// NOSONAR
import fr.aresrpg.commons.domain.log.Logger;

public class UnsafeAccessor {
	private static final Unsafe unsafe = stealUnsafe();

	private UnsafeAccessor() {throw new IllegalConstructionException();}

	private static Unsafe stealUnsafe() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe) f.get(null);
		} catch (ReflectiveOperationException e) {
			Logger.MAIN_LOGGER.severe(e, "Unsafe not found , library not usable");
			return null;
		}
	}

	/**
	 * Get the unsafe instance
	 * @return unsafe
	 * @see Unsafe
	 */
	public static Unsafe getUnsafe() {
		return unsafe;
	}
}