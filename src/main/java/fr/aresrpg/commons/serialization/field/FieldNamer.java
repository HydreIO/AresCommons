package fr.aresrpg.commons.serialization.field;

@FunctionalInterface
public interface FieldNamer {
	String transform(String name);
}
