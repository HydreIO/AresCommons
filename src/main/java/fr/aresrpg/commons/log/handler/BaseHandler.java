package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.log.handler.formatters.ErrorFormatter;
import fr.aresrpg.commons.log.handler.formatters.Formatter;

import java.io.IOException;

public abstract class BaseHandler implements Handler {
	private Formatter formatter;
	private ErrorFormatter errorFormatter;

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public void setErrorFormatter(ErrorFormatter errorFormatter) {
		this.errorFormatter = errorFormatter;
	}


	public Formatter getFormatter() {
		return formatter;
	}

	public ErrorFormatter getErrorFormatter() {
		return errorFormatter;
	}


	@Override
	public void handle(Logger.Level level, String channel , String message, Throwable t , long millis) throws IOException {
		log(level , formatter.format(level ,channel, message , t == null ? null : errorFormatter.formatError(level , t) , millis));
	}

	abstract void log(Logger.Level level, String message) throws IOException;
}
