package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamHandler extends BaseHandler{
	private PrintStream outStream;
	private PrintStream errorStream;

	public PrintStreamHandler(PrintStream outStream, PrintStream errorStream) {
		this.outStream = outStream;
		this.errorStream = errorStream;
	}

	public PrintStreamHandler(PrintStream stream) {
		this(stream, stream);
	}

	@Override
	public void handle(Log log) throws IOException {
		if (log.getLevel().isError())
			errorStream.print(format(log));
		else
			outStream.print(format(log));
	}
}
