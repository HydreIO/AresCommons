package fr.aresrpg.commons.domain.serialization.adapters;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;

public class EnumAdapter<T extends Enum> implements Adapter<T, String> { //TODO: Better enum support
	public static final ParametrizedClass<String> OUT = new ParametrizedClass<String>() {};

	private Class<T> enumClass;

	public EnumAdapter(Class<T> enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	public String adaptTo(T in) {
		if (in == null) return null;
		return in.name();
	}

	@Override
	public T adaptFrom(String out) {
		if (out == null || out.equalsIgnoreCase("null")) return null;
		for (T t : enumClass.getEnumConstants())
			if (t.name().equals(out))
				return t;
		throw new IllegalStateException("Enum value " + out + " do not exist in enum " + enumClass);
	}

	@Override
	public ParametrizedClass<T> getInType() {
		return new ParametrizedClass<>(enumClass);
	}

	@Override
	public ParametrizedClass<String> getOutType() {
		return OUT;
	}
}
