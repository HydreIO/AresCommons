package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;
import java.io.OutputStream;


/**
 * An handler using streams
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public class StreamHandler extends BaseHandler {
	private OutputStream outStream;
	private OutputStream errorStream;
	private String charset;

	/**
	 * Create a stream handler using passed streams and charset
	 * @param outStream the output stream stdout
	 * @param errorStream the error stream stderr
	 * @param charset the charset
	 */
	public StreamHandler(OutputStream outStream, OutputStream errorStream, String charset) {
		this.outStream = outStream;
		this.errorStream = errorStream;
		this.charset = charset;
	}

	/**
	 * Create a stream handler using passed streams and charset UTF-8
	 * @param outStream the output stream stdout
	 * @param errorStream the error stream stderr
	 */
	public StreamHandler(OutputStream outStream, OutputStream errorStream) {
		this(outStream, errorStream, "UTF-8");
	}

	/**
	 * Create a stream handler using passed stream
	 * @param stream the stream to use
	 */
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
