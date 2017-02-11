package fr.aresrpg.commons.domain.reflection;

import java.lang.reflect.Modifier;

/**
 * An util class for reflection
 */
public class Reflection {
	private Reflection(){}

	public static boolean isInnerClass(Class clazz){
		return (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()));
	}

	public static boolean isVolatileClass(Class clazz){
		return Modifier.isVolatile(clazz.getModifiers());
	}
}
