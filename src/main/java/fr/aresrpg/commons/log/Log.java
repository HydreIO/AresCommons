package fr.aresrpg.commons.log;

public class Log {
	private Logger.Level level;
	private String channel;
	private String message;
	private String baseMessage;
	private Object[] args;
	private Throwable throwable;
	private long millis;
	private Thread thread;
	private StackTraceElement source;
	private Logger logger;

	public Log(Logger.Level level, String channel, String message, String baseMessage, Object[] args, Throwable throwable, long millis, Thread thread, StackTraceElement source, Logger logger) {
		this.level = level;
		this.channel = channel;
		this.message = message;
		this.baseMessage = baseMessage;
		this.args = args;
		this.throwable = throwable;
		this.millis = millis;
		this.thread = thread;
		this.source = source;
		this.logger = logger;
	}

	public Logger.Level getLevel() {
		return level;
	}

	public String getChannel() {
		return channel;
	}

	public String getMessage() {
		return message;
	}

	public String getBaseMessage() {
		return baseMessage;
	}

	public Object[] getArgs() {
		return args;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public long getMillis() {
		return millis;
	}

	public Thread getThread() {
		return thread;
	}

	public StackTraceElement getSource() {
		return source;
	}

	public Logger getLogger() {
		return logger;
	}
}
