package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.log.handler.formatters.BasicFormatter;
import fr.aresrpg.commons.log.handler.formatters.ErrorFormatter;
import fr.aresrpg.commons.log.handler.formatters.Formatter;

public abstract class BaseHandler implements Handler {
	private Formatter formatter = new BasicFormatter();
	private ErrorFormatter errorFormatter = new ErrorFormatter() {
		@Override
		public String formatError(Logger.Level level, Throwable t) {
			return "";
		}
	};

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
