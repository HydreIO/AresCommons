package fr.aresrpg.commons.serialization.field;

import java.lang.reflect.Field;

public class ReflectionFieldModifier implements FieldModifier{
	@Override
	public void setValue(Field field, Object instance, Object value) {
		field.setAccessible(true);
		try {
			field.set(instance , value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unreachable" , e);
		}
	}

	@Override
	public Object getValue(Field field, Object instance) {
		field.setAccessible(true);
		try {
			return field.get(instance);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unreachable" , e);
		}
	}

	@Override
	public boolean canProccess(Field f) {
		return true;
	}
}
