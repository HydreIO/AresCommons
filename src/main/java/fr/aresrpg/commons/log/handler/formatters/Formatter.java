package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.Log;

@FunctionalInterface
public interface Formatter {
	String format(Log log , ErrorFormatter errorFormatter);
}
