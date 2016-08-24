package fr.aresrpg.commons.domain.log;

/**
 * A logger message with is context
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
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

	/**
	 * Create a new logged message
	 * @param level the level of this log
	 * @param channel the channel witch the message is logged
	 * @param message the message of this log
	 * @param baseMessage the base message without replaced variables
	 * @param args the args passed to this log
	 * @param throwable the throwable of this log
	 * @param millis the time of the log
	 * @param thread the thread with the log has bean created
	 * @param source the source stack trace
	 * @param logger the logger used
	 */
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

	/**
	 * Get the level of this log
	 * @return the level
	 */
	public Logger.Level getLevel() {
		return level;
	}

	/**
	 * Get the channel of this log
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Get the message of this log
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get the message without argument replaced of this log
	 * @return the message
	 */
	public String getBaseMessage() {
		return baseMessage;
	}

	/**
	 * Get the args passed to this log
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * Get the throwable of this log
	 * @return the throwable of this log
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * Get the time of the log
	 * @return the time
	 */
	public long getMillis() {
		return millis;
	}

	/**
	 * Get the thread of this log
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Get the source of this log
	 * @return the source
	 */
	public StackTraceElement getSource() {
		return source;
	}

	/**
	 * Get the logger of this log
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}
}
