package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Log;

@FunctionalInterface
public interface Formatter {
	String format(Log log , ErrorFormatter errorFormatter);
}
