package fr.aresrpg.commons.domain.log.handler;

import fr.aresrpg.commons.domain.log.Log;

import java.io.IOException;

/**
 * A log handler
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface Handler {
	/**
	 * Handle the log sent by a logger
	 * @param log the log
	 * @throws IOException if an exception occurred when writing the log
	 */
	void handle(Log log) throws IOException;
}
