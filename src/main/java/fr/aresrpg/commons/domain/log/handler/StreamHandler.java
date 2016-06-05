package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;
import java.io.OutputStream;

public class StreamHandler extends BaseHandler {
	private OutputStream outStream;
	private OutputStream errorStream;
	private String charset;

	public StreamHandler(OutputStream outStream, OutputStream errorStream, String charset) {
		this.outStream = outStream;
		this.errorStream = errorStream;
		this.charset = charset;
	}

	public StreamHandler(OutputStream outStream, OutputStream errorStream) {
		this(outStream, errorStream, "UTF-8");
	}

	public StreamHandler(OutputStream stream) {
		this(stream, stream);
	}

	@Override
	public void handle(Log log) throws IOException {
		if (log.getLevel().isError()) {
			errorStream.write(format(log).getBytes(charset));
			errorStream.flush();
		} else {
			outStream.write(format(log).getBytes(charset));
			outStream.flush();
		}
	}
}
