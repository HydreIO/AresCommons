package fr.aresrpg.commons.domain.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A ByteBuffer Input stream
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ByteBufferInputStream extends InputStream {
	private final ByteBuffer buffer;

	/**
	 * Create a new byte buffer input stream from the provided buffer
	 * 
	 * @param buffer
	 *            the byte buffer to use
	 */
	public ByteBufferInputStream(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		return buffer.get();
	}

	@Override
	public int read(byte[] b) throws IOException {
		buffer.get(b);
		return b.length;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		buffer.get(b, off, len);
		return len;
	}
}
