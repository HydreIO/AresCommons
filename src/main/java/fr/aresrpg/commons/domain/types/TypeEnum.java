package fr.aresrpg.commons.domain.types;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * A enum to represent commons types
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public enum TypeEnum {
	//Primitive
	BOOLEAN,
	BYTE,
	SHORT,
	CHAR,
	INT,
	LONG,
	FLOAT,
	DOUBLE,
	NULL,

	//Base
	STRING,
	COLLECTION,
	MAP,

	//Arrays
	OBJECT_ARRAY,
	BOOLEAN_ARRAY,
	BYTE_ARRAY,
	SHORT_ARRAY,
	CHAR_ARRAY,
	INT_ARRAY,
	LONG_ARRAY,
	FLOAT_ARRAY,
	DOUBLE_ARRAY,

	//Last
	OBJECT;

	/**
	 * Get the type enum for this class
	 * @param clazz the class
	 * @return the type enum for the class
	 */
	public static TypeEnum getType(Class<?> clazz){
		String name = clazz.getName();
		TypeEnum type = getType(name);
		if(type != null)
			return type;
		else if(clazz.isArray()){
			if(clazz.getComponentType().isPrimitive())
				switch (getPrimitiveType(clazz.getComponentType().getName())){
					case BOOLEAN:
						return BOOLEAN_ARRAY;
					case BYTE:
						return BYTE_ARRAY;
					case SHORT:
						return SHORT_ARRAY;
					case CHAR:
						return CHAR_ARRAY;
					case INT:
						return INT_ARRAY;
					case LONG:
						return LONG_ARRAY;
					case FLOAT:
						return FLOAT_ARRAY;
					case DOUBLE:
						return DOUBLE_ARRAY;
					default:
						throw new IllegalStateException("Unreachable");
				}
			else
				return OBJECT_ARRAY;
		}else if(name.startsWith("java.util"))
			if(Collection.class.isAssignableFrom(clazz))
				return COLLECTION;
			else if(Map.class.isAssignableFrom(clazz))
				return MAP;
			else return OBJECT;
		else
			return OBJECT;
	}

	/**
	 * Get the type enum for this class name
	 * @param name the name of the class
	 * @return the type enum for the class
	 */
	public static TypeEnum getType(String name){
		switch (name){
			case "java.lang.String":
				return STRING;
			case "java.lang.Boolean":
				return BOOLEAN;
			case "java.lang.Byte":
				return BYTE;
			case "java.lang.Short":
				return SHORT;
			case "java.lang.Character":
				return CHAR;
			case "java.lang.Integer":
				return INT;
			case "java.lang.Long":
				return LONG;
			case "java.lang.Float":
				return FLOAT;
			case "java.lang.Double":
				return DOUBLE;
			default:
				return getPrimitiveType(name);
		}
	}

	public static TypeEnum getPrimitiveType(Class clazz){
		Objects.requireNonNull(clazz);
		TypeEnum value = getPrimitiveType(clazz.getName());
		return value == null ? OBJECT : value;
	}

	public static TypeEnum getPrimitiveType(String name){
		switch (name){
			case "boolean":
				return BOOLEAN;
			case "byte":
				return BYTE;
			case "short":
				return SHORT;
			case "char":
				return CHAR;
			case "int":
				return INT;
			case "long":
				return LONG;
			case "float":
				return FLOAT;
			case "double":
				return DOUBLE;
			default:
				return null;
		}
	}
	public static TypeEnum getType(Object object){
		if(object == null)
			return NULL;
		else
			return getType(object.getClass());
	}
}
