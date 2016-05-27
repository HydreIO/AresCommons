package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.handler.formatters.ErrorFormatter;
import fr.aresrpg.commons.log.handler.formatters.Formatter;

public abstract class BaseHandler implements Handler {
	private Formatter formatter;
	private ErrorFormatter errorFormatter = (e , l) -> "";

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

	protected String format(Log log){
		return formatter.format(log , errorFormatter);
	}
}
