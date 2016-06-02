package fr.aresrpg.commons.serialization.factory;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.Serializer;
import fr.aresrpg.commons.serialization.adapters.Adapter;
import fr.aresrpg.commons.serialization.field.FieldModifier;

import java.util.List;

public interface SerializationFactory {
	<T> Serializer<T> createSerializer(Class<T> clazz);
	<T> Serializer<T> createOrGetSerializer(Class<T> clazz);

	List<Adapter<? , ?>> getAdapters();
	void addAdapter(Adapter<? , ?> adapter);
	void removeAdapter(Adapter<? , ?> adapter);
	<T> Adapter<T , ?> getAdapter(ParametrizedClass<T> clazz);

	FieldModifier getFieldModifier();
	void setFieldModifier(FieldModifier modifier);
}
