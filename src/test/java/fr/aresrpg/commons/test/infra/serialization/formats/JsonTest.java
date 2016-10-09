package fr.aresrpg.commons.test.infra.serialization.formats;

import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.infra.serialization.formats.JsonFormat;
import fr.aresrpg.commons.infra.serialization.unsafe.UnsafeSerializationFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class JsonTest {

	private static class JsonObjectTest {
		private Long[] array;
		private String string;
		private Number number;
		private Object nullValue;
		private boolean bool;
		private JsonObjectTest object;
	}

	private static final String JSON = "{\n" +
			"   \"array\":[1, 2, 3],\n" +
			"   \"string\":\"Hello\",\n" +
			"   \"number\":42,\n" +
			"   \"nullValue\":null,\n" +
			"   \"bool\":true,\n" +
			"   \"object\":{\n" +
			"      \"array\":[1, 2, 3],\n" +
			"      \"string\":\"Hello\",\n" +
			"      \"number\":42,\n" +
			"      \"nullValue\":null,\n" +
			"      \"bool\":true\n" +
			"   }\n" +
			"}";
	@Test
	public void parseJson() throws IOException {
		SerializationFactory factory = new UnsafeSerializationFactory();
		Serializer<JsonObjectTest> s = factory.createOrGetSerializer(JsonObjectTest.class);
		testObject(s.deserialize(new ByteArrayInputStream(JSON.getBytes("UTF-8")) , JsonFormat.INSTANCE) , true);
	}

	private void testObject(JsonObjectTest test , boolean deep){
		String prefix = deep ? "object." : "";
		Assert.assertArrayEquals(prefix + "array must be [1 , 2 , 3]" , new Integer[]{1 , 2 , 3} , test.array);
		Assert.assertEquals(prefix + "string must be \"Hello\"" , "Hello" , test.string);
		Assert.assertEquals(prefix + "number must be 42" , 42 , test.number);
		Assert.assertNull(prefix + "nullValue must be null" , test.nullValue);
		Assert.assertTrue(prefix + "bool must be true" , test.bool);
		if(deep)
			testObject(test.object , false);
	}

	@Test
	public void deserialize_array() throws IOException {
		SerializationFactory factory = new UnsafeSerializationFactory();
		Serializer<Integer[]> s = factory.createOrGetSerializer(Integer[].class);
		Assert.assertArrayEquals(new Integer[]{1 , 2 , 3} ,
				s.deserialize(new ByteArrayInputStream("[1 , 2 , 3]".getBytes("UTF-8")) ,
						JsonFormat.INSTANCE));
	}
}
