package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
	void handle(Log log) throws IOException;
}
