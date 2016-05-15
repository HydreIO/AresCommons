package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Logger;

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
		this(outStream, errorStream , "UTF-8");
	}

	public StreamHandler(OutputStream stream) {
		this(stream , stream);
	}

	@Override
	public void log(Logger.Level level, String message) throws IOException{
		if(level.isError()){
			errorStream.write(message.getBytes(charset));
			errorStream.flush();
		}else {
			outStream.write(message.getBytes(charset));
			outStream.flush();
		}
	}
}
