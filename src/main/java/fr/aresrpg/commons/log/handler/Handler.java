package fr.aresrpg.commons.log.handler;

import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.Logger;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
	void handle(Log log) throws IOException;
}
