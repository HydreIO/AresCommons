package fr.aresrpg.commons.test.infra.serialization;

import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class SerializerTest {

	private static class DeserializeMapTestObject {
		private String name;

		String hello() {
			return "Hello " + name;
		}
	}

	@Test
	public void deserialize_map() throws IOException {
		SerializationFactory factory = createFactory();
		Serializer<DeserializeMapTestObject> s = factory.createOrGetSerializer(DeserializeMapTestObject.class);
		Map<String , Object> map = new HashMap<>();
		map.put("name" , "world");

		Assert.assertEquals("Object must have same fields as described in the map",
							"Hello world",
							s.deserialize(map).hello());
	}

	private static class DeserializeComplexMapTestObject {
		private DeserializeMapTestObject object;

		DeserializeMapTestObject getObject() {
			return object;
		}
	}

	@Test
	public void deserialize_complex_map() throws IOException {
		SerializationFactory factory = createFactory();
		Serializer<DeserializeComplexMapTestObject> s = factory.createOrGetSerializer(DeserializeComplexMapTestObject.class);
		Map<String , Object> child = new HashMap<>();
		child.put("name" , "world");
		Map<String , Object> map = new HashMap<>();
		map.put("object" , child);

		Assert.assertEquals("Object must have same fields as described in the map",
				"Hello world",
				s.deserialize(map).getObject().hello());
	}

	protected abstract SerializationFactory createFactory();
}
