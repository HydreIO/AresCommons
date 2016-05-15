package fr.aresrpg.commons.log.handler;

public class ConsoleHandler extends StreamHandler{
	public ConsoleHandler(String charset) {
		super(System.out, System.out, charset);//NOSONAR It's a logger
	}

	public ConsoleHandler() {
		super(System.out);//NOSONAR It's a logger
	}
}
