package fr.aresrpg.commons.domain.serialization.annotations;

import fr.aresrpg.commons.domain.serialization.FieldNamer;

import java.lang.reflect.Field;

public class SerializedNameFieldNamer implements FieldNamer{
	@Override
	public String getName(Field field) {
		SerializedName sname = field.getAnnotation(SerializedName.class);
		return sname == null ? field.getName() : sname.value();
	}
}
