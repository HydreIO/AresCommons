package fr.aresrpg.commons.domain.util;

import fr.aresrpg.commons.domain.util.exception.IllegalConstructionException;

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
	 * Try to convert a Type to a class
	 * 
	 * @param type
	 *            the type to convert
	 * @param <T>
	 *            The type of the class to return
	 * @return the class represented by this type or null if not found
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClazz(Type type) {
		if (type instanceof Class<?>) return (Class<T>) type;
		else if (type instanceof ParameterizedType) return (Class<T>) ((ParameterizedType) type).getRawType();
		else return null;
	}
}
