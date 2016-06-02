package fr.aresrpg.commons.serialization.adapters;

import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.reflection.ParametrizedClass;

public class ClassAdapter implements Adapter<Class<?> , String> {
	public static final Adapter<Class<?> , String> INSTANCE = new ClassAdapter();
	public static final ParametrizedClass<Class<?>> IN = new ParametrizedClass<Class<?>>(){};
	public static final ParametrizedClass<String> OUT = new ParametrizedClass<String>(){};

	private ClassAdapter(){}

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
