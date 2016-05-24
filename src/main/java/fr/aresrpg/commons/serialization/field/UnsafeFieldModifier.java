package fr.aresrpg.commons.serialization.field;

import fr.aresrpg.commons.unsafe.UnsafeAccessor;

import java.lang.reflect.Field;

public class UnsafeFieldModifier implements FieldModifier{

	@Override
	public void setValue(Field field, Object instance, Object value) {
		UnsafeAccessor.getUnsafe().putObject(instance , UnsafeAccessor.getUnsafe().objectFieldOffset(field) , value);
	}

	@Override
	public Object getValue(Field field, Object instance) {
		return UnsafeAccessor.getUnsafe().getObject(instance , UnsafeAccessor.getUnsafe().objectFieldOffset(field));
	}

	@Override
	public boolean canProccess(Field f) {
		return true;
	}
}
