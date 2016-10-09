package fr.aresrpg.commons.domain.log;

import fr.aresrpg.commons.domain.condition.Option;
import fr.aresrpg.commons.domain.log.handler.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A logger
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class Logger {
	/**
	 * The logger of AresCommons
	 */
	public static final Logger MAIN_LOGGER = new LoggerBuilder("DEFAULT").setUseConsoleHandler(true, true, Option.none(), Option.none())
			.build();
	/**
	 * The default channel of the logs
	 */
	public static final String DEFAULT_CHANEL = null;

	/**
	 * The pattern to parse args
	 */
	public static final Pattern ARGS_PATTERN = Pattern.compile("\\{(\\d+)?(:\\?)?\\}");

	/**
	 * The logger levels
	 * 
	 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
	 */
	public enum Level {
		INFO(false),
		SUCCESS(false),
		WARNING(false),
		DEBUG(false),
		ERROR(true),
		SEVERE(true);

		private final boolean error;

		Level(boolean error) {
			this.error = error;
		}

		public boolean isError() {
			return error;
		}
	}

	private final String name;
	private final List<Handler> handlers;

	/**
	 * Create a new logger
	 * 
	 * @param name
	 *            the name of the logger
	 * @param handlers
	 *            the handlers of this logger
	 */
	public Logger(String name, List<Handler> handlers) {
		this.name = name;
		this.handlers = handlers;
	}

	public Logger(String name) {
		this(name, new ArrayList<>());
	}

	/**
	 * Send a log to the handlers
	 * 
	 * @param level
	 *            the level of the log
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void log(Level level, String channel, Throwable t, String message, Object... args) {
		broadcast(level, channel, message, args, t);
	}

	/**
	 * Send a log to the handlers to the default channel
	 * 
	 * @param level
	 *            the level of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void log(Level level, Throwable t, String message, Object... args) {
		log(level, DEFAULT_CHANEL, t, message, args);
	}

	/**
	 * Send a log to the handlers
	 * 
	 * @param level
	 *            the level of the log
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void log(Level level, String channel, String message, Object... args) {
		log(level, channel, null, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel
	 * 
	 * @param level
	 *            the level of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void log(Level level, String message, Object... args) {
		log(level, DEFAULT_CHANEL, message, args);
	}

	/**
	 * Send a log to the handlers
	 * 
	 * @param level
	 *            the level of the log
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void log(Level level, String channel, Throwable t) {
		log(level, channel, t, "");
	}

	/**
	 * Send a log to the handlers to the default channel
	 * 
	 * @param level
	 *            the level of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void log(Level level, Throwable t) {
		log(level, DEFAULT_CHANEL, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#INFO} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void info(String channel, Throwable t, String message, Object... args) {
		log(Level.INFO, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#INFO} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void info(Throwable t, String message, Object... args) {
		log(Level.INFO, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#INFO} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void info(String channel, String message, Object... args) {
		log(Level.INFO, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#INFO} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void info(String message, Object... args) {
		log(Level.INFO, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#INFO} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void info(String channel, Throwable t) {
		log(Level.INFO, channel, t);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#INFO} level
	 * 
	 * @param t
	 *            the throwable of the log
	 */
	public void info(Throwable t) {
		log(Level.INFO, DEFAULT_CHANEL, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#SUCCESS} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void success(String channel, Throwable t, String message, Object... args) {
		log(Level.SUCCESS, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#SUCCESS} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void success(Throwable t, String message, Object... args) {
		log(Level.SUCCESS, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#SUCCESS} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void success(String channel, String message, Object... args) {
		log(Level.SUCCESS, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#SUCCESS} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void success(String message, Object... args) {
		log(Level.SUCCESS, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#SUCCESS} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void success(String channel, Throwable t) {
		log(Level.SUCCESS, channel, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#WARNING} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void warning(String channel, Throwable t, String message, Object... args) {
		log(Level.WARNING, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#WARNING} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void warning(Throwable t, String message, Object... args) {
		log(Level.WARNING, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#WARNING} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void warning(String channel, String message, Object... args) {
		log(Level.WARNING, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#WARNING} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void warning(String message, Object... args) {
		log(Level.WARNING, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#WARNING} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void warning(String channel, Throwable t) {
		log(Level.WARNING, channel, t);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#WARNING} level
	 * 
	 * @param t
	 *            the throwable of the log
	 */
	public void warning(Throwable t) {
		log(Level.WARNING, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#DEBUG} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void debug(String channel, Throwable t, String message, Object... args) {
		log(Level.DEBUG, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#DEBUG} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void debug(Throwable t, String message, Object... args) {
		log(Level.DEBUG, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#DEBUG} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void debug(String channel, String message, Object... args) {
		log(Level.DEBUG, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#DEBUG} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void debug(String message, Object... args) {
		log(Level.DEBUG, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#DEBUG} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void debug(String channel, Throwable t) {
		log(Level.DEBUG, channel, t);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#DEBUG} level
	 * 
	 * @param t
	 *            the throwable of the log
	 */
	public void debug(Throwable t) {
		log(Level.DEBUG, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#ERROR} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void error(String channel, Throwable t, String message, Object... args) {
		log(Level.ERROR, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#ERROR} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void error(Throwable t, String message, Object... args) {
		log(Level.ERROR, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#ERROR} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void error(String channel, String message, Object... args) {
		log(Level.ERROR, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#ERROR} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void error(String message, Object... args) {
		log(Level.ERROR, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#ERROR} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void error(String channel, Throwable t) {
		log(Level.ERROR, channel, t);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#ERROR} level
	 * 
	 * @param t
	 *            the throwable of the log
	 */
	public void error(Throwable t) {
		log(Level.ERROR, t);
	}

	/**
	 * Send a log to the handlers with {@link Level#SEVERE} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void severe(String channel, Throwable t, String message, Object... args) {
		log(Level.SEVERE, channel, t, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#SEVERE} level
	 * 
	 * @param t
	 *            the throwable of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void severe(Throwable t, String message, Object... args) {
		log(Level.SEVERE, t, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#SEVERE} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void severe(String channel, String message, Object... args) {
		log(Level.SEVERE, channel, message, args);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#SEVERE} level
	 * 
	 * @param message
	 *            the message of the log
	 * @param args
	 *            the args of the message
	 */
	public void severe(String message, Object... args) {
		log(Level.SEVERE, message, args);
	}

	/**
	 * Send a log to the handlers with {@link Level#SEVERE} level
	 * 
	 * @param channel
	 *            the channel of the log
	 * @param t
	 *            the throwable of the log
	 */
	public void severe(String channel, Throwable t) {
		log(Level.SEVERE, channel, t);
	}

	/**
	 * Send a log to the handlers to the default channel with {@link Level#SEVERE} level
	 * 
	 * @param t
	 *            the throwable of the log
	 */
	public void severe(Throwable t) {
		log(Level.SEVERE, t);
	}

	private String processArgs(String message, Object... args) {
		if (args == null) return message;
		StringBuilder sb = new StringBuilder();
		Matcher matcher = ARGS_PATTERN.matcher(message);
		int index = 0;
		int i = 0;
		while (true) {
			if (!matcher.find()) {
				sb.append(message.substring(index));
				return sb.toString();
			}
			String n = matcher.group(1);
			sb.append(message.substring(index, matcher.start()));
			if (args.length != 0) sb.append(args[n == null ? i++ : Integer.parseInt(n)]);
			index = matcher.end();
		}
	}

	/**
	 * Add an handler to this logger
	 * 
	 * @param handler
	 *            the handler to add
	 */
	public void addHandler(Handler handler) {
		handlers.add(handler);
	}

	private void broadcast(Level level, String channel, String message, Object[] args, Throwable t) {
		Log log = new Log(level, channel, processArgs(message, args), message, args, t, System.currentTimeMillis(), Thread.currentThread(), findSource(), this);
		try {
			for (Handler handler : handlers)
				handler.handle(log);
		} catch (IOException e) {
			MAIN_LOGGER.severe(e);
		}
	}

	private StackTraceElement findSource() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		for (int i = 1; i < elements.length; i++) {
			StackTraceElement element = elements[i];
			if (!element.getClassName().equals(getClass().getName())) // Ignore logger calls
				return element;
		}
		return null;
	}

	/**
	 * Get the name of this logger
	 * 
	 * @return the name of this logger
	 */
	public String getName() {
		return name;
	}
}