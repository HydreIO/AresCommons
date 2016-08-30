package fr.aresrpg.commons.domain.serialization;

import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.unsafe.UnsafeAccessor;

/**
 * A class creator
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface InstanceConstructor {
	/**
	 * The default {@link InstanceConstructor}
	 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
	 */
	class Creators{
		/**
		 * A creator using unsafe
		 */
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

	/**
	 * Create a new instance of the provided class
	 * @param clazz the class
	 * @return a instance of the class
	 */
	Object create(Class<?> clazz);
}
