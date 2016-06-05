package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Logger;

@FunctionalInterface
public interface ErrorFormatter {
	String formatError(Logger.Level level , Throwable t);
}
