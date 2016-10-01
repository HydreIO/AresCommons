package fr.aresrpg.commons.domain.serialization;

import java.lang.reflect.Field;

/**
 * A field name
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface FieldNamer {
	/**
	 * Get the name of the field
	 * 
	 * @param field the source field
	 * @return a transformed name
	 */
	String getName(Field field);
}
