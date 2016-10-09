package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;
import fr.aresrpg.commons.domain.log.handler.formatters.ErrorFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.Formatter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * An abstract implementation of an handler providing some formatters
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public abstract class BaseHandler implements Handler {
	private Formatter formatter;

	private ErrorFormatter errorFormatter = ErrorFormatter.DEFAULT;

	/**
	 * Set the log formatter
	 * 
	 * @param formatter
	 *            the log formatter
	 */
	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	/**
	 * Set the log error formatter
	 * 
	 * @param errorFormatter
	 *            the formatter
	 */
	public void setErrorFormatter(ErrorFormatter errorFormatter) {
		this.errorFormatter = errorFormatter;
	}

	/**
	 * Get the log formatter
	 * 
	 * @return the log formatter
	 */
	public Formatter getFormatter() {
		return formatter;
	}

	/**
	 * Get the log error formatter
	 * 
	 * @return the error formatter
	 */
	public ErrorFormatter getErrorFormatter() {
		return errorFormatter;
	}

	/**
	 * Format log as string using formatter
	 * 
	 * @param log
	 *            the log to transform
	 * @return a formatted log
	 * @throws NullPointerException
	 *             when the formatter is null
	 */
	protected String format(Log log) throws NullPointerException {
		if (formatter == null) throw new NullPointerException("Logger formatter not found");
		return formatter.format(log, errorFormatter);
	}

}
