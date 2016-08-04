package fr.aresrpg.commons.domain.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * A Byte Buffer Output Stream
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ByteBufferOutputStream extends OutputStream {
	private final ByteBuffer buffer;

	/**
	 * Create a new byte buffer output stream from the provided buffer
	 * @param buffer the byte buffer to use
	 */
	public ByteBufferOutputStream(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void write(int b) throws IOException {
		buffer.put((byte) b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		buffer.put(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.put(b, off, len);
	}

}
