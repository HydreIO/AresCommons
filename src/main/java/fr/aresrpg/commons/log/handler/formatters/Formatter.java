package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.Logger;

@FunctionalInterface
public interface Formatter {
	String format(Logger.Level level , String channel , String message , String error , long millis);
}
