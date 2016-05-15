package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.Logger;

@FunctionalInterface
public interface ErrorFormatter {
	String formatError(Logger.Level level , Throwable t);
}
