package fr.aresrpg.commons.domain.log.handler;

/**
 * A console handler
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public class ConsoleHandler extends PrintStreamHandler{
	/**
	 * Create a new Console handler that use stdout and stderr
	 */
	public ConsoleHandler() {
		super(System.out , System.err);//NOSONAR It's a logger
	}
}
