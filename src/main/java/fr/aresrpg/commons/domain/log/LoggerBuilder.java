package fr.aresrpg.commons.domain.log;

import fr.aresrpg.commons.domain.builder.Builder;
import fr.aresrpg.commons.domain.log.handler.ConsoleHandler;
import fr.aresrpg.commons.domain.log.handler.Handler;
import fr.aresrpg.commons.domain.log.handler.formatters.BasicFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.ColorFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.Formatter;

import java.util.ArrayList;
import java.util.List;

public class LoggerBuilder implements Builder<Logger>{
	private boolean colored;
	private Formatter formatter;
	private boolean useConsoleHandler;
	private List<Handler> handlers;
	private final String name;

	public LoggerBuilder(String name) {
		this.name = name;
		this.colored = false;
		this.formatter = null;
		this.handlers = new ArrayList<>();
	}

	public LoggerBuilder setUseConsoleHandler(boolean use , boolean colored) {
		this.useConsoleHandler = use;
		this.colored = colored;
		return this;
	}

	public LoggerBuilder addHandler(Handler handler){
		handlers.add(handler);
		return this;
	}

	@Override
	public Logger build() {
		if(formatter == null)
			formatter = new BasicFormatter();
		if(useConsoleHandler){
			ConsoleHandler handler = new ConsoleHandler();
			if(colored)
				handler.setFormatter(new ColorFormatter(formatter));
			else
				handler.setFormatter(formatter);
			handlers.add(handler);
		}
		return new Logger(name , handlers);
	}
}
