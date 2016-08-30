package fr.aresrpg.commons.domain.serialization.field;

/**
 * A field name
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface FieldNamer {
	/**
	 * Transform the name of the field
	 * @param name the source name
	 * @return a transformed name
	 */
	String transform(String name);
}
