package fr.aresrpg.commons.unsafe;

import fr.aresrpg.commons.log.Logger;
import sun.misc.Unsafe;// NOSONAR

import java.lang.reflect.Field;

public class UnsafeAccessor {
	private static final Unsafe unsafe = stealUnsafe();

	private UnsafeAccessor(){}

	private static Unsafe stealUnsafe(){
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe) f.get(null);
		} catch (ReflectiveOperationException e) {
			Logger.MAIN_LOGGER.severe(e , "Unsafe not found , library not usable");
			return null;
		}
	}

	static Unsafe getUnsafe() {
		return unsafe;
	}
}