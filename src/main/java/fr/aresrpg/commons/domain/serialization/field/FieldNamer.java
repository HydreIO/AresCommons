package fr.aresrpg.commons.domain.serialization.field;

@FunctionalInterface
public interface FieldNamer {
	String transform(String name);
}
