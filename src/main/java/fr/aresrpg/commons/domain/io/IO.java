package fr.aresrpg.commons.domain.io;

import fr.aresrpg.commons.domain.util.exception.IllegalConstructionException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A IO Util class
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class IO {
	/**
	 * The default charset of AresCommons
	 */
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	/**
	 * The default charset name of AresCommons
	 */
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * The default buffer size
	 */
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private IO() {
		throw new IllegalConstructionException();
	}

	/**
	 * Copy the input stream to output stream
	 * 
	 * @param from
	 *            the source input stream
	 * @param to
	 *            the destination output stream
	 * @param bufferSize
	 *            the size of the buffer used
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static void copy(InputStream from, OutputStream to, int bufferSize) throws IOException {
		int read;
		byte[] buff = new byte[bufferSize];

		while ((read = from.read(buff, 0, buff.length)) != -1)
			to.write(buff, 0, read);
		to.flush();
	}

	/**
	 * Copy the input stream to output stream
	 * 
	 * @param from
	 *            the source input stream
	 * @param to
	 *            the destination output stream
	 * @throws IOException
	 *             if an io exception occur
	 */
	public static void copy(InputStream from, OutputStream to) throws IOException {
		copy(from, to, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Transform the input stream to a byte array
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @param bufferSize
	 *            the size of the buffer
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occur
	 */
	public static byte[] toByteArray(InputStream in, int bufferSize) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(32, in.available()));
		copy(in, out, bufferSize);
		return out.toByteArray();
	}

	/**
	 * Transform the input stream to a byte array
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static byte[] toByteArray(InputStream in) throws IOException {
		return toByteArray(in, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Transform the input stream to a string
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @param charset
	 *            the charset to use
	 * @param bufferSize
	 *            the size of the buffer
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public String toString(InputStream in, String charset, int bufferSize) throws IOException {
		return new String(toByteArray(in, bufferSize), charset);
	}

	/**
	 * Transform the input stream to a string
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public String toString(InputStream in) throws IOException {
		return toString(in, DEFAULT_CHARSET_NAME, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Transform the base64 input stream to a byte array
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @param bufferSize
	 *            the size of the buffer
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static byte[] toBase64(InputStream in, int bufferSize) throws IOException {
		return Base64.getEncoder().encode(toByteArray(in, bufferSize));
	}

	/**
	 * Transform the base64 input stream to a byte array
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static byte[] toBase64(InputStream in) throws IOException {
		return toBase64(in, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Transform the input stream to a base64 string
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @param charset
	 *            the charset to use
	 * @param bufferSize
	 *            the size of the buffer
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static String toBase64String(InputStream in, String charset, int bufferSize) throws IOException {
		return new String(toBase64(in, bufferSize), charset);
	}

	/**
	 * Transform the input stream to a base64 string
	 * 
	 * @param in
	 *            read the input stream to a byte array
	 * @return a byte array from the input stream
	 * @throws IOException
	 *             if an io exception occurred
	 */
	public static String toBase64String(InputStream in) throws IOException {
		return toBase64String(in, DEFAULT_CHARSET_NAME, DEFAULT_BUFFER_SIZE);
	}
}
