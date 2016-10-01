package fr.aresrpg.commons.infra.serialization.unsafe;

import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.factory.AbstractSerializationFactory;

public class UnsafeSerializationFactory extends AbstractSerializationFactory {
	@Override
	protected <T> Serializer<T> createSerializerInstance(Class<T> clazz) {
		return new UnsafeSerializer<>(clazz , this);
	}
}
