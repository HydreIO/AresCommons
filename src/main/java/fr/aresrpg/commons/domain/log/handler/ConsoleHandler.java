package fr.aresrpg.commons.domain.log.handler;

/**
 * A console handler
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public class ConsoleHandler extends PrintStreamHandler{
	public ConsoleHandler() {
		super(System.out , System.err);//NOSONAR It's a logger
	}
}
