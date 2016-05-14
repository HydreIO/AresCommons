package fr.aresrpg.commons.reflection;

import java.util.HashSet;
import java.util.Set;

public class Reflection {
    private static final Set<Class<?>> NUMBERS_TYPES = new HashSet<>();
    private Reflection(){

    }

    static {
        NUMBERS_TYPES.add(Byte.class);
        NUMBERS_TYPES.add(byte.class);
        NUMBERS_TYPES.add(Short.class);
        NUMBERS_TYPES.add(short.class);
        NUMBERS_TYPES.add(Integer.class);
        NUMBERS_TYPES.add(int.class);
        NUMBERS_TYPES.add(Long.class);
        NUMBERS_TYPES.add(long.class);
        NUMBERS_TYPES.add(Float.class);
        NUMBERS_TYPES.add(float.class);
        NUMBERS_TYPES.add(Double.class);
        NUMBERS_TYPES.add(double.class);
    }

    public static boolean isNumber(Class<?> clazz){
        return NUMBERS_TYPES.contains(clazz);
    }

    public static boolean isBoolean(Class<?> clazz){
        return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    public static boolean isCharacter(Class<?> clazz){
        return Character.class.equals(clazz) || char.class.equals(clazz);
    }


}
