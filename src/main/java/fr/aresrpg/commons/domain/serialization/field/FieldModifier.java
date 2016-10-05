package fr.aresrpg.commons.domain.serialization.field;

import java.lang.reflect.Field;

/**
 * A field modifier
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface FieldModifier {

	/**
	 * Set value of the field
	 * 
	 * @param field
	 *            the field to set value
	 * @param instance
	 *            the instance of the field's owner
	 * @param value
	 *            the value to set
	 */
	void setValue(Field field, Object instance, Object value);

	/**
	 * Get the value of a field
	 * 
	 * @param field
	 *            the field to get value
	 * @param instance
	 *            the instance of the field's owner
	 * @return the value of a field
	 */
	Object getValue(Field field, Object instance);

	/**
	 * Test if this field can be processed
	 * 
	 * @param f
	 *            the field
	 * @return true if the field can be processed
	 */
	boolean canProcess(Field f);

	/**
	 * Preprocess a field
	 * 
	 * @param field
	 *            the field to preprocess
	 */
	void preprocess(Field field);
}
