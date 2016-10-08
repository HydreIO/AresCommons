package fr.aresrpg.commons.test.infra.serialization;

import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.infra.serialization.unsafe.UnsafeSerializationFactory;

public class UnsafeSerializationTest extends SerializerTest{

	@Override
	protected SerializationFactory createFactory() {
		return new UnsafeSerializationFactory();
	}
}
