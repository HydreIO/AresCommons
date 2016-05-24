package fr.aresrpg.commons.serialization.formats.json;

import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.SerializationContext;
import fr.aresrpg.commons.serialization.formats.SerializationFormat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JsonSerializationFormat implements SerializationFormat{
	public static final String ENCODING = "UTF-8";
	public static final char BEGIN_OBJECT = '{';
	public static final char END_OBJECT = '}';
	public static final char SEPARATOR = ':';
	public static final char STRING_DELIMITER = '"';
	public static final char BEGIN_ARRAY = '[';
	public static final char END_ARRAY = ']';
	public static final char ARRAY_SEPARATOR = ',';
	public static final char FIELD_SEPARATOR = ',';
	private static final byte[] JSON_TRUE = getBytes("true");
	private static final byte[] JSON_FALSE = getBytes("false");
	private static final byte[] JSON_NULL = getBytes("null");

	public static byte[] getBytes(String string){
		try {
			return string.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			Logger.MAIN_LOGGER.severe("JsonSerializationFormat" , e , "UTF-8 encoding not found");
			return new byte[0];
		}
	}

	@Override
	public <T> void writeObject(OutputStream outputStream, T value, SerializationContext context) throws IOException {
		outputStream.write(BEGIN_OBJECT);
		SerializationFormat.super.writeObject(outputStream , value , context);
		outputStream.write(END_OBJECT);
	}

	@Override
	public void writeField(OutputStream outputStream, String name, Object value, SerializationContext context, boolean last) throws IOException {
		writeString(outputStream , name , context);
		outputStream.write(SEPARATOR);
		write(outputStream , value , context);
		if(!last)
			outputStream.write(FIELD_SEPARATOR);
	}

	@Override
	public void writeNull(OutputStream outputStream, SerializationContext context) throws IOException {
		outputStream.write(JSON_NULL);
	}

	@Override
	public void writeNumber(OutputStream outputStream, Number value, SerializationContext context) throws IOException {
		outputStream.write(value.toString().getBytes(ENCODING));
	}

	@Override
	public void writeBoolean(OutputStream outputStream, boolean value, SerializationContext context) throws IOException {
		outputStream.write(value ? JSON_TRUE : JSON_FALSE);
	}

	@Override
	public void writeString(OutputStream outputStream, String value, SerializationContext context) throws IOException {
		outputStream.write(STRING_DELIMITER);
		outputStream.write(value.getBytes(ENCODING));
		outputStream.write(STRING_DELIMITER);
	}

	@Override
	public void writeArray(OutputStream outputStream, List<?> array, SerializationContext context) throws IOException {
		outputStream.write(BEGIN_ARRAY);
		int end = array.size()-1;
		for(int i = 0 ; i < array.size() ; i++){
			write(outputStream , array.get(i) , context);
			if(i != end)
				outputStream.write(ARRAY_SEPARATOR);
		}
		outputStream.write(END_ARRAY);
	}
}
