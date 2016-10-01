package fr.aresrpg.commons.domain.config;

import fr.aresrpg.commons.domain.serialization.FieldNamer;

import java.lang.reflect.Field;

public class ConfiguredFieldNamer implements FieldNamer{
	@Override
	public String getName(Field field) {
		Configured c = field.getAnnotation(Configured.class);
		return c == null ? field.getName() :
				c.value().endsWith(".") ? c.value() + field.getName() : c.value();
	}
}
