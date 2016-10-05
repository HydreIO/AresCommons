package fr.aresrpg.commons.domain.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represent a writable input
 * 
 * @since 0.6
 */
public class WritableInputStream extends InputStream {
	@Override
	public int read() throws IOException {
		throw new UnsupportedOperationException();
	}
}
