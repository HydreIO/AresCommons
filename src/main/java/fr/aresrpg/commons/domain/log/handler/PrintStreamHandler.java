package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;
import java.io.PrintStream;

/**
 * An handler using print stream
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class PrintStreamHandler extends BaseHandler {
	private PrintStream outStream;
	private PrintStream errorStream;

	/**
	 * Create a new PrintStream Handler
	 * 
	 * @param outStream
	 *            the output {@link PrintStream} (stdout)
	 * @param errorStream
	 *            the error {@link PrintStream} (stderr)
	 */
	public PrintStreamHandler(PrintStream outStream, PrintStream errorStream) {
		this.outStream = outStream;
		this.errorStream = errorStream;
	}

	/**
	 * Create a new PrintStream Handler with same stdout and stderr
	 * 
	 * @param stream
	 *            the output {@link PrintStream}
	 */
	public PrintStreamHandler(PrintStream stream) {
		this(stream, stream);
	}

	@Override
	public void handle(Log log) throws IOException {
		if (log.getLevel().isError()) errorStream.println(format(log));
		else outStream.println(format(log));
	}
}
