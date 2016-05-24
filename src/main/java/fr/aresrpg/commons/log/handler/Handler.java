package fr.aresrpg.commons.log.handler;

import java.io.IOException;

import fr.aresrpg.commons.log.Log;

@FunctionalInterface
public interface Handler {
	void handle(Log log) throws IOException;
}
