package fr.aresrpg.commons.domain.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Types {
	private Types(){}
	@SuppressWarnings("unchecked")
	public static<T> Class<T> getClazz(Type type){
		if(type instanceof Class<?>)
			return (Class<T>)type;
		else if(type instanceof ParameterizedType)
			return (Class<T>)((ParameterizedType)type).getRawType();
		else return null;
	}
}
