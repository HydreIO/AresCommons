package fr.aresrpg.commons.domain.log;

import fr.aresrpg.commons.domain.builder.Builder;
import fr.aresrpg.commons.domain.condition.Option;
import fr.aresrpg.commons.domain.functional.suplier.Supplier;
import fr.aresrpg.commons.domain.log.handler.ConsoleHandler;
import fr.aresrpg.commons.domain.log.handler.Handler;
import fr.aresrpg.commons.domain.log.handler.formatters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A logger builder
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class LoggerBuilder implements Builder<Logger> {
	private List<Supplier<Handler>> handlers;
	private final String name;

	/**
	 * Create a new logger builder
	 * 
	 * @param name
	 *            the name of the future builded logger
	 */
	public LoggerBuilder(String name) {
		this.name = name;
		this.handlers = new ArrayList<>();
	}

	/**
	 * Add a console handler to this logger
	 * 
	 * @param use
	 *            if the logger must use the console handler
	 * @param colored
	 *            if the logger must proxy the formatter with a {@link ColorFormatter}
	 * @param formatter
	 *            the formatter if needed
	 * @param errorFormatter
	 *            the error formatter if needed
	 * @return this
	 * @see #addHandler(Handler)
	 */
	public LoggerBuilder setUseConsoleHandler(boolean use, boolean colored, Option<Formatter> formatter, Option<ErrorFormatter> errorFormatter) {
		if (!use) return this;
		handlers.add(() -> {
			ConsoleHandler handler = new ConsoleHandler();
			Formatter fm = formatter.orElse(colored ? new ColorFormatter(new BasicFormatter()) : new BasicFormatter());
			errorFormatter.ifPresent(handler::setErrorFormatter);
			handler.setFormatter(fm);
			return handler;
		});
		return this;
	}

	/**
	 * Add an handler to the logger
	 * 
	 * @param handler
	 *            the handler to add
	 * @return this
	 */
	public LoggerBuilder addHandler(Handler handler) {
		handlers.add(() -> handler);
		return this;
	}

	/**
	 * Add an handler to the logger
	 * </p>
	 * we use a supplier to execute actions only in the build method
	 * 
	 * @param handler
	 *            the handler to add
	 * @return this
	 */
	public LoggerBuilder addHandler(Supplier<Handler> handler) {
		handlers.add(handler);
		return this;
	}

	/**
	 * Build the logger
	 * 
	 * @return a builded logger
	 */
	@Override
	public Logger build() {
		return new Logger(name, handlers.stream().map(Supplier::get).collect(Collectors.toList()));
	}
}
