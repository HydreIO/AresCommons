package fr.aresrpg.commons.domain.serialization.factory;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.adapters.Adapter;
import fr.aresrpg.commons.domain.serialization.field.FieldModifier;

import java.util.List;

/**
 * A serialization factory that create {@link Serializer}
 * 
 * @param <I>
 *            the input type
 * @param <O>
 *            the output type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface SerializationFactory<I, O> {
	<T> Serializer<T, I, O> createSerializer(Class<T> clazz);

	<T> Serializer<T, I, O> createOrGetSerializer(Class<T> clazz);

	List<Adapter<?, ?>> getAdapters();

	void addAdapter(Adapter<?, ?> adapter);

	void removeAdapter(Adapter<?, ?> adapter);

	<T> Adapter<T, ?> getAdapter(ParametrizedClass<T> clazz);

	FieldModifier getFieldModifier();

	void setFieldModifier(FieldModifier modifier);
}
