package fr.aresrpg.commons.domain.serialization.field;

import java.lang.reflect.Field;

public interface FieldModifier {
	void setValue(Field field , Object instance , Object value);
	Object getValue(Field field , Object instance);
	boolean canProcess(Field f);

	void preprocess(Field field);
}
