package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Logger;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
	void handle(Logger.Level level , String channel , String message , Throwable t , long millis) throws IOException;
}
