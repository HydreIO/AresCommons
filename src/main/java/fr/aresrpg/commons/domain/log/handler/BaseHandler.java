package fr.aresrpg.commons.domain.log.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

import fr.aresrpg.commons.domain.log.Log;
import fr.aresrpg.commons.domain.log.handler.formatters.ErrorFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.Formatter;

public abstract class BaseHandler implements Handler {
	private Formatter formatter;
	// private ErrorFormatter errorFormatter = (e , l) -> "";

	private ErrorFormatter errorFormatter = (e, l) -> {
		if (l == null) return "[" + e.name() + "] Exception without cause just throwed !";
		StringWriter sw = new StringWriter(1024);
		final PrintWriter pw = new PrintWriter(sw);
		l.printStackTrace(pw);
		pw.flush();
		return "[" + e.name() + "] " + sw.toString();
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

	protected String format(Log log) {
		return formatter.format(log, errorFormatter);
	}

}
