package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A default implementation of {@link Formatter}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class BasicFormatter implements Formatter {
	/**
	 * The default format of this formatter used with {@link #BasicFormatter()}
	 */
	public static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("HH:mm:ss");

	private DateFormat dateFormat;

	/**
	 * Create a new formatter using the provided DateFormat
	 * 
	 * @param dateFormat
	 *            the format to use
	 */
	public BasicFormatter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * Create a new formatter using the default DateFormat {@link BasicFormatter#DEFAULT_FORMAT}
	 */
	public BasicFormatter() {
		this(DEFAULT_FORMAT);
	}

	/**
	 * Get the date format used by this formatter
	 * 
	 * @return the date format used
	 */
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * Set the date format to use
	 * 
	 * @param dateFormat
	 *            the date format to use
	 */
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String format(Log log, ErrorFormatter errorFormatter) {
		StringBuilder sb = new StringBuilder()
						.append('[')
						.append(dateFormat.format(new Date(log.getMillis())))
						.append(']').append('[')
						.append(log.getThread().getName())
						.append(']').append('[')
						.append(log.getLevel()).append(']');
		if(log.getChannel() != null)
			sb.append('[').append(log.getChannel()).append(']');
		sb.append(": ").append(log.getMessage());
		if(!log.getMessage().isEmpty() && log.getThrowable() != null)
			sb.append('\n');
		if(log.getThrowable() != null)
			sb.append(errorFormatter.formatError(log.getLevel(), log.getThrowable()));
		return sb.toString();
	}
}
