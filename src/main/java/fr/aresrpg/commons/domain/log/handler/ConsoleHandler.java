package fr.aresrpg.commons.domain.log.handler;

public class ConsoleHandler extends PrintStreamHandler{
	public ConsoleHandler() {
		super(System.out);//NOSONAR It's a logger
	}
}
