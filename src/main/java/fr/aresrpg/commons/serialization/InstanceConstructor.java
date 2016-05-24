package fr.aresrpg.commons.serialization;

import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.unsafe.UnsafeAccessor;

@FunctionalInterface
public interface InstanceConstructor {
	class Creators{
		public static final InstanceConstructor UNSAFE_CREATOR = c -> {
			try {
				return UnsafeAccessor.getUnsafe().allocateInstance(c);
			} catch (InstantiationException e) {
				Logger.MAIN_LOGGER.severe(e, "Error with Unsafe and class " + c);
				return null;
			}
		};
		private Creators(){}
	}

	Object create(Class<?> clazz);
}
