package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicFormatter implements Formatter{
	private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("HH:mm:ss");

	private DateFormat dateFormat;

	public BasicFormatter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public BasicFormatter() {
		this(DEFAULT_FORMAT);
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String format(Log log , ErrorFormatter errorFormatter) {
		return '[' + dateFormat.format(new Date(log.getMillis())) + "]["
				+ log.getThread().getName() + "][" + log.getLevel() +"]"+(log.getChannel() != null ? '[' + log.getChannel() + ']' : "")+
				": " + log.getMessage() + '\n' + (log.getThrowable() == null ? "" : errorFormatter.formatError(log.getLevel() , log.getThrowable()));
	}
}
