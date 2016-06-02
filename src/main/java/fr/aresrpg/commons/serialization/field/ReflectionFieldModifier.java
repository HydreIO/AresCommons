package fr.aresrpg.commons.serialization.field;

import java.lang.reflect.Field;

public class ReflectionFieldModifier implements FieldModifier{
	@Override
	public void setValue(Field field, Object instance, Object value) {
		try {
			field.set(instance , value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unreachable" , e);
		}
	}

	@Override
	public Object getValue(Field field, Object instance) {
		try {
			return field.get(instance);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unreachable" , e);
		}
	}

	@Override
	public boolean canProcess(Field f) {
		return true;
	}

	@Override
	public void preprocess(Field field) {
		field.setAccessible(true);
	}
}
