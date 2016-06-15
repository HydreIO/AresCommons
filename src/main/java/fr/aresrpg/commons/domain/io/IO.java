package fr.aresrpg.commons.domain.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

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
}
