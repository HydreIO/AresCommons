package fr.aresrpg.commons.infra.serialization.formats;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import fr.aresrpg.commons.domain.io.IO;
import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.Format;
import fr.aresrpg.commons.domain.types.TypeEnum;

public class JsonFormat implements Format<InputStream, OutputStream> {
	public static final JsonFormat INSTANCE = new JsonFormat();
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
	public static final char BEGIN_TRUE = 't';
	public static final char BEGIN_FALSE = 'f';
	public static final char BEGIN_NULL = 'n';

	private JsonFormat() {
	}

	public static byte[] getBytes(String string) {
		try {
			return string.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			Logger.MAIN_LOGGER.severe("JsonFormat", e, "UTF-8 encoding not found");
			return new byte[0];
		}
	}

	@Override
	public void writeBegin(OutputStream out) throws IOException {
		// Ignore
	}

	@Override
	public void writeValue(OutputStream out, String name, TypeEnum type, Object value, SerializationContext context) throws IOException {
		if (name != null) {
			out.write(STRING_DELIMITER);
			out.write(name.getBytes(ENCODING));
			out.write(STRING_DELIMITER);
			out.write(SEPARATOR);
		}
		switch (type) {
			case BOOLEAN:
				out.write((Boolean) value ? JSON_TRUE : JSON_FALSE);
				break;
			case BYTE:
			case SHORT:
			case INT:
			case LONG:
			case DOUBLE:
			case FLOAT:
				out.write(value.toString().getBytes(ENCODING));
				break;
			case CHAR:
				out.write(STRING_DELIMITER);
				out.write((Character) value);
				out.write(STRING_DELIMITER);
				break;
			case NULL:
				out.write(JSON_NULL);
				break;
			case STRING:
				out.write(STRING_DELIMITER);
				out.write(((String) value).getBytes(ENCODING));
				out.write(STRING_DELIMITER);
				break;
			case COLLECTION:
				writeCollection(out, (Collection<?>) value, context);
				break;
			case OBJECT_ARRAY:
				writeObjectArray(out, (Object[]) value , context);
				break;
			case BYTE_ARRAY:
				writeByteArray(out, (byte[]) value);
				break;
			case SHORT_ARRAY:
				writeShortArray(out, (short[]) value);
				break;
			case CHAR_ARRAY:
				writeCharArray(out, (char[]) value);
				break;
			case INT_ARRAY:
				writeIntArray(out, (int[]) value);
				break;
			case LONG_ARRAY:
				writeLongArray(out, (long[]) value);
				break;
			case FLOAT_ARRAY:
				writeFloatArray(out, (float[]) value);
				break;
			case DOUBLE_ARRAY:
				writeDoubleArray(out, (double[]) value);
				break;
			case OBJECT:
				context.serialize(out , value , this);
			default:
				break;
		}
	}


	@Override
	public void writeBeginObject(OutputStream out) throws IOException {
		out.write(BEGIN_OBJECT);
	}

	@Override
	public void writeFieldSeparator(OutputStream out, boolean firstField, boolean lastField) throws IOException {
		if (!lastField) out.write(FIELD_SEPARATOR);
	}

	@Override
	public void writeEndObject(OutputStream out) throws IOException {
		out.write(END_OBJECT);
	}

	@Override
	public void writeEnd(OutputStream out) throws IOException {
		// Ignore
	}

	public void writeCollection(OutputStream out, Collection<?> collection, SerializationContext context) throws IOException {
		out.write(BEGIN_ARRAY);
		Iterator<?> it = collection.iterator();
		if (it.hasNext()) {
			while (true) {
				context.serialize(out, it.next(), this);
				if (!it.hasNext()) break;
				else out.write(ARRAY_SEPARATOR);
			}
		}
		out.write(END_ARRAY);
	}

	private void writeObjectArray(OutputStream out, Object[] objects, SerializationContext context) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = objects.length - 1;
		for (int i = 0; i < objects.length; i++) {
			context.serialize(out , objects[i] , this);
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeByteArray(OutputStream out, byte[] bytes) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = bytes.length - 1;
		for (int i = 0; i < bytes.length; i++) {
			out.write(Byte.toString(bytes[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeShortArray(OutputStream out, short[] shorts) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = shorts.length - 1;
		for (int i = 0; i < shorts.length; i++) {
			out.write(Short.toString(shorts[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeCharArray(OutputStream out, char[] chars) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = chars.length - 1;
		for (int i = 0; i < chars.length; i++) {
			out.write(chars[i]);
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeIntArray(OutputStream out, int[] ints) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = ints.length - 1;
		for (int i = 0; i < ints.length; i++) {
			out.write(Integer.toString(ints[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeLongArray(OutputStream out, long[] longs) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = longs.length - 1;
		for (int i = 0; i < longs.length; i++) {
			out.write(Long.toString(longs[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeFloatArray(OutputStream out, float[] floats) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = floats.length - 1;
		for (int i = 0; i < floats.length; i++) {
			out.write(Float.toString(floats[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	public void writeDoubleArray(OutputStream out, double[] doubles) throws IOException {
		out.write(BEGIN_ARRAY);
		int end = doubles.length - 1;
		for (int i = 0; i < doubles.length; i++) {
			out.write(Double.toString(doubles[i]).getBytes(ENCODING));
			if (i != end) out.write(ARRAY_SEPARATOR);
		}
		out.write(END_ARRAY);
	}

	@Override
	public void read(InputStream in, Map<String, Object> container, SerializationContext context) throws IOException {
		container.putAll(parseObjectContent(in));
	}

	private Map<String, Object> parseObjectContent(InputStream in) throws IOException {
		Map<String , Object> map = new HashMap<>();
		assumeToken(in, BEGIN_OBJECT);
		while(true) {
			String name = readStringContent(in);
			assumeToken(in , SEPARATOR);
			map.put(name , parseValue(in));
			char c;
			switch (( c = nextToken(in , false))){
				case FIELD_SEPARATOR:
					continue;
				case END_OBJECT:
					return map;
				default:
					throw new IOException("Found illegal character " + c);
			}
		}
	}

	private String readStringContent(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		assumeToken(in , STRING_DELIMITER);
		while(true){
			char c = (char) in.read();
			if(c == STRING_DELIMITER)
				break;
			else
				sb.append(c);
		}
		return sb.toString();
	}

	private Object parseValue(InputStream in) throws  IOException {
		Object value;
		char c;
		switch ((c = nextToken(in ,true))) {
			case BEGIN_OBJECT:
				value = parseObjectContent(in);
				break;
			case BEGIN_ARRAY:
				value = parseArrayContent(in);
				break;
			case STRING_DELIMITER:
				value = readStringContent(in);
				break;
			case BEGIN_TRUE:
				checkTokenEq(in , JSON_TRUE);
				value = true;
				break;
			case BEGIN_FALSE:
				checkTokenEq(in , JSON_FALSE);
				value = true;
				break;
			case BEGIN_NULL:
				checkTokenEq(in , JSON_NULL);
				value = null;
				break;
			default:
				if(c >= '0' && c <= '9')
					value = parseNumber(in);
				else
					throw new IOException("Found illegal character " + c);
		}
		return value;
	}

	private Object parseNumber(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		while(true){
			in.mark(1);
			char c = (char) in.read();
			if(c >= '0' && c <= '9')
				sb.append(c);
			else
				break;
		}
		in.reset();
		long l = Long.parseLong(sb.toString());
		if(l <= Integer.MAX_VALUE && l >= Integer.MIN_VALUE)
			return (int)l;
		else
			return l;
	}

	private Object[] parseArrayContent(InputStream in) throws IOException {
		List<Object> list = new ArrayList<>();
		assumeToken(in , BEGIN_ARRAY);
		while(true) {
			list.add(parseValue(in));
			char c;
			switch (( c = nextToken(in , false))){
				case ARRAY_SEPARATOR:
					continue;
				case END_ARRAY:
					return list.toArray(new Object[list.size()]);
				default:
					throw new IOException("Found illegal character " + c);
			}
		}

	}

	private void checkTokenEq(InputStream in, byte[] bytes) throws IOException{
		int c;
		for(int i = 0 ; i < bytes.length ; i++)
			if((c = in.read()) != bytes[i])
				throw new IOException("Expected " + (char)bytes[i] + " but found " + (char)c);
	}

	private char nextToken(InputStream in , boolean reset) throws IOException {
		int c;
		do {
			if(reset)in.mark(1);
			c = in.read();
		} while (c == '\n' || c == ' '|| c == '\t');
		if(reset)in.reset();
		return (char) c;
	}

	private void assumeToken(InputStream in , char e) throws IOException {
		char c = nextToken(in , false);
		if(c != e)
			throw new IOException("Expected " + e + " but found " + c);

	}
}
