package fr.aresrpg.commons.domain.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class IO {
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private IO(){}

	public static void copy(InputStream from , OutputStream to , int bufferSize) throws IOException {
		int read;
		byte[] buff = new byte[bufferSize];

		while ((read = from.read(buff, 0, buff.length)) != -1)
			to.write(buff, 0, read);
		to.flush();
	}

	public static void copy(InputStream from , OutputStream to) throws IOException {
		copy(from, to, DEFAULT_BUFFER_SIZE);
	}

	public static byte[] toByteArray(InputStream in , int bufferSize) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(32, in.available()));
		copy(in , out , bufferSize);
		return out.toByteArray();
	}

	public static byte[] toByteArray(InputStream in) throws IOException{
		return toByteArray(in , DEFAULT_BUFFER_SIZE);
	}

	public String toString(InputStream in , String charset , int bufferSize) throws IOException {
		return new String(toByteArray(in , bufferSize) , charset);
	}

	public String toString(InputStream in) throws IOException {
		return toString(in , DEFAULT_CHARSET_NAME , DEFAULT_BUFFER_SIZE);
	}

	public static byte[] toBase64(InputStream in , int bufferSize) throws IOException {
		return Base64.getEncoder().encode(toByteArray(in , bufferSize));
	}

	public static byte[] toBase64(InputStream in) throws IOException{
		return toBase64(in , DEFAULT_BUFFER_SIZE);
	}


	public static String toBase64String(InputStream in , String charset , int bufferSize) throws IOException {
		return new String(toBase64(in , bufferSize) , charset);
	}

	public static String toBase64String(InputStream in) throws IOException {
		return toBase64String(in , DEFAULT_CHARSET_NAME , DEFAULT_BUFFER_SIZE);
	}
}
