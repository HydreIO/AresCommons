package fr.aresrpg.commons.serialization.formats;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.reflection.Reflection;
import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.util.ArrayUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public interface SerializationFormat {

	default <T> void write(OutputStream outputStream , T value , SerializationContext context) throws IOException {
		if(value == null)
			writeNull(outputStream , context);
		else if(value.getClass().isArray())
			writeArray(outputStream , asList(value) , context);
		else if(Reflection.isNumber(value.getClass()))
			writeNumber(outputStream , (Number) value , context);
		else if(Reflection.isCharacter(value.getClass()))
			writeChar(outputStream , (Character) value , context);
		else if(Reflection.isBoolean(value.getClass()))
			writeBoolean(outputStream , (Boolean) value , context);
		else if(value.getClass() == String.class)
			writeString(outputStream , (String) value , context);
		else if(Collection.class.isAssignableFrom(value.getClass()))
			writeCollection(outputStream , (Collection<?>) value , context);
		else
			context.serializeObject(outputStream , value , new ParametrizedClass<T>(value.getClass()));
	}

	default <T> void writeObject(OutputStream outputStream , T value , SerializationContext context) throws IOException {
		context.deepSerialize(outputStream , value);
	}

	void writeField(OutputStream outputStream, String name, Object value, SerializationContext context, boolean last) throws IOException;

	void writeNull(OutputStream outputStream, SerializationContext context) throws IOException;

	void writeNumber(OutputStream outputStream , Number value, SerializationContext context) throws IOException;

	default void writeChar(OutputStream outputStream , char value, SerializationContext context) throws IOException {
		writeString(outputStream , Character.toString(value) , context);
	}

	void writeBoolean(OutputStream outputStream , boolean value, SerializationContext context) throws IOException;

	void writeString(OutputStream outputStream , String value, SerializationContext context) throws IOException;

	void writeArray(OutputStream outputStream , List<?> array, SerializationContext context) throws IOException;

	default void writeCollection(OutputStream outputStream , Collection<?> collection, SerializationContext context) throws IOException {
		writeArray(outputStream , Collections.unmodifiableList(new ArrayList<>(collection)) , context);
	}

	static List<?> asList(Object array){
		if(Object[].class.isAssignableFrom(array.getClass()))
			return Arrays.asList((Object[])array);
		else if(array.getClass() == byte[].class)
			return ArrayUtils.asList((byte[])array);
		else if(array.getClass() == short[].class)
			return ArrayUtils.asList((short[])array);
		else if(array.getClass() == int[].class)
			return ArrayUtils.asList((int[])array);
		else if(array.getClass() == long[].class)
			return ArrayUtils.asList((long[])array);
		else if(array.getClass() == float[].class)
			return ArrayUtils.asList((float[])array);
		else if(array.getClass() == double[].class)
			return ArrayUtils.asList((double[])array);
		else
			throw new UnsupportedOperationException(array.getClass() + " is not an array");
	}
}
