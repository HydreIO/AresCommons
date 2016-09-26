package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Log;

/**
 * A {@link Log} Formatter , it transform {@link Log} into a {@link String}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface Formatter {
	/**
	 * Format a log into a String
	 * 
	 * @param log
	 *            the log to format
	 * @param errorFormatter
	 *            the error formatter
	 * @return a String representation of the log
	 */
	String format(Log log, ErrorFormatter errorFormatter);
}
