package fr.aresrpg.commons.types;

import java.util.Collection;

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

	public static TypeEnum getType(Class<?> clazz){
		TypeEnum value = null;
		if(clazz.isPrimitive())
			value = getPrimitiveType(clazz);
		else if(clazz.isArray()){
			if(clazz.getComponentType().isPrimitive())
				switch (getPrimitiveType(clazz.getComponentType())){
					case BOOLEAN:
						value = BOOLEAN_ARRAY;
						break;
					case BYTE:
						value = BYTE_ARRAY;
						break;
					case SHORT:
						value = SHORT_ARRAY;
						break;
					case CHAR:
						value = CHAR_ARRAY;
						break;
					case INT:
						value = INT_ARRAY;
						break;
					case LONG:
						value = LONG_ARRAY;
						break;
					case FLOAT:
						value = FLOAT_ARRAY;
						break;
					case DOUBLE:
						value = DOUBLE_ARRAY;
						break;
					default:
						break;
				}
			else
				value = OBJECT_ARRAY;
		} else {
			if(clazz.getName().startsWith("java.lang")){
				if(String.class.equals(clazz))
					value = STRING;
				else
					value = getWrapperType(clazz);
			} else if(clazz.getName().startsWith("java.util"))
				if(Collection.class.isAssignableFrom(clazz))
					value = COLLECTION;
		}
		return value == null ? OBJECT : value;
	}

	public static TypeEnum getType(Object object){
		if(object == null)
			return NULL;
		else
			return getType(object.getClass());
	}

	public static TypeEnum getPrimitiveType(Class<?> clazz){
		TypeEnum value = null;
		if(boolean.class.equals(clazz))
			value = BOOLEAN;
		else if(byte.class.equals(clazz))
			value = BYTE;
		else if(short.class.equals(clazz))
			value = SHORT;
		else if(char.class.equals(clazz))
			value = CHAR;
		else if(int.class.equals(clazz))
			value = INT;
		else if(long.class.equals(clazz))
			value = LONG;
		else if(float.class.equals(clazz))
			value = FLOAT;
		else if(double.class.equals(clazz))
			value = DOUBLE;
		return value;

	}

	public static TypeEnum getWrapperType(Class<?> clazz){
		TypeEnum value = null;
		if(Boolean.class.equals(clazz))
			value = BOOLEAN;
		else if(Byte.class.equals(clazz))
			value = BYTE;
		else if(Short.class.equals(clazz))
			value = SHORT;
		else if(Character.class.equals(clazz))
			value = CHAR;
		else if(Integer.class.equals(clazz))
			value = INT;
		else if(Long.class.equals(clazz))
			value = LONG;
		else if(Float.class.equals(clazz))
			value = FLOAT;
		else if(Double.class.equals(clazz))
			value = DOUBLE;
		return value;

	}



}
