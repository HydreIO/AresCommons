package fr.aresrpg.commons.domain.log;

import fr.aresrpg.commons.domain.builder.Builder;
import fr.aresrpg.commons.domain.log.handler.ConsoleHandler;
import fr.aresrpg.commons.domain.log.handler.Handler;
import fr.aresrpg.commons.domain.log.handler.formatters.BasicFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.ColorFormatter;
import fr.aresrpg.commons.domain.log.handler.formatters.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * A logger builder
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public class LoggerBuilder implements Builder<Logger>{
	private boolean colored;
	private Formatter formatter;
	private boolean useConsoleHandler;
	private List<Handler> handlers;
	private final String name;

	/**
	 * Create a new logger builder
	 * @param name the name of the future builded logger
	 */
	public LoggerBuilder(String name) {
		this.name = name;
		this.colored = false;
		this.formatter = null;
		this.handlers = new ArrayList<>();
	}

	/**
	 * Add a console handler to this logger
	 * @param use if the logger must use the console handler
	 * @param colored if the logger must proxy the formatter with a {@link ColorFormatter}
	 * @return this
	 * @see #addHandler(Handler)
	 */
	public LoggerBuilder setUseConsoleHandler(boolean use , boolean colored) {
		this.useConsoleHandler = use;
		this.colored = colored;
		return this;
	}

	/**
	 * Add an handler to add to the logger on build
	 * @param handler the handler to add
	 * @return this
	 */
	public LoggerBuilder addHandler(Handler handler){
		handlers.add(handler);
		return this;
	}

	/**
	 * Build the logger
	 * @return a builded logger
	 */
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
