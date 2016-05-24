package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Log;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
	void handle(Log log) throws IOException;
}
