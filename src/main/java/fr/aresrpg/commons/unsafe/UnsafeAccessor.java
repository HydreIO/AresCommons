package fr.aresrpg.commons.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;// NOSONAR
import fr.aresrpg.commons.log.Logger;

public class UnsafeAccessor {
	private static final Unsafe unsafe = stealUnsafe();

	private UnsafeAccessor() {
	}

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

	public static Unsafe getUnsafe() {
		return unsafe;
	}
}