package fr.aresrpg.commons.domain.io;

import java.io.IOException;
import java.io.InputStream;

public class WritableInputStream extends InputStream{
	@Override
	public int read() throws IOException {
		throw new UnsupportedOperationException();
	}
}
