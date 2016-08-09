package fr.aresrpg.commons.domain.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * An util class to use with Type class
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class Types {
	private Types() {
		throw new IllegalConstructionException();
	}

	/**
	 * Convert a Type if it's possible to a class or null if it's not possible
	 * 
	 * @param type
	 *            the type to convert
	 * @param <T>
	 *            The type of return class
	 * @return null or the class represented by this type
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClazz(Type type) {
		if (type instanceof Class<?>) return (Class<T>) type;
		else if (type instanceof ParameterizedType) return (Class<T>) ((ParameterizedType) type).getRawType();
		else return null;
	}
}
