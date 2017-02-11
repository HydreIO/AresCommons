package fr.aresrpg.commons.infra.serialization;

import fr.aresrpg.commons.domain.serialization.Format;
import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;

import java.io.IOException;

public class BasicSerializationContext implements SerializationContext {
	private final SerializationFactory factory;

	public BasicSerializationContext(SerializationFactory factory) {
		this.factory = factory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T, O> void serialize(O stream, T value, Format<?, O> format) throws IOException {
		factory.createOrGetSerializer((Class<T>) value.getClass()).serialize(stream, value, format);
	}
}
