package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Logger;

/**
 * A {@link Throwable} Formatter , it transform {@link Throwable} into a {@link String}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface ErrorFormatter {
	/**
	 * Transform the {@link Throwable} into a {@link String}
	 * 
	 * @param level
	 *            the level of the error
	 * @param t
	 *            the throwable to format
	 * @return a representation of this error
	 */
	String formatError(Logger.Level level, Throwable t);
}
