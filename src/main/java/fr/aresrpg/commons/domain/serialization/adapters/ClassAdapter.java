package fr.aresrpg.commons.domain.serialization.adapters;

import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.reflection.ParametrizedClass;

/**
 * An adapter {@link Class} to {@link String}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ClassAdapter implements Adapter<Class<?>, String> {
	/**
	 * The instance of this adapter
	 */
	public static final Adapter<Class<?>, String> INSTANCE = new ClassAdapter();

	/**
	 * The input type of this adapter
	 */
	public static final ParametrizedClass<Class<?>> IN = new ParametrizedClass<Class<?>>() {};

	/**
	 * The output type of this adapter
	 */
	public static final ParametrizedClass<String> OUT = new ParametrizedClass<String>() {};

	private ClassAdapter() {
	}

	@Override
	public String adaptTo(Class<?> in) {
		return in.getName();
	}

	@Override
	public Class<?> adaptFrom(String out) {
		try {
			return Class.forName(out);
		} catch (ClassNotFoundException e) {
			Logger.MAIN_LOGGER.severe("ClassAdapter", "Class " + out + " not found");
			return null;
		}
	}

	@Override
	public ParametrizedClass<Class<?>> getInType() {
		return IN;
	}

	@Override
	public ParametrizedClass<String> getOutType() {
		return OUT;
	}
}
