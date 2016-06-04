package fr.aresrpg.commons.log.handler;

public class ConsoleHandler extends PrintStreamHandler{
	public ConsoleHandler() {
		super(System.out);//NOSONAR It's a logger
	}
}
