package fr.aresrpg.commons.domain.concurrent;

import fr.aresrpg.commons.domain.builder.Builder;
import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.log.Logger.Level;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

/**
 * An thread builder
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class ThreadBuilder implements Builder<Thread> {

	private Runnable runnable;
	private String name;
	private Boolean daemon;
	private Integer priority;
	private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
	// Hold the number of threads launched with this builder
	private AtomicInteger counter = new AtomicInteger(0);

	public ThreadBuilder setRunnable(Runnable runnable) {
		this.runnable = runnable;
		return this;
	}

	/**
	 * Set %1$ to get the number of the thread ,see the <a
	 * href="../util/Formatter.html#detail">Details</a> section of the
	 * formatter class specification.
	 * 
	 * @param name
	 *            the thread name
	 * @return the thread builder instance
	 */
	public ThreadBuilder setName(String name) {
		Objects.requireNonNull(name);
		this.name = name;
		return this;
	}

	/**
	 * Get the name to set to this thread
	 * 
	 * @return the thread name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the thread as daemon
	 * 
	 * @param daemon
	 *            thread daemon
	 * @return the thread builder instance
	 */
	public ThreadBuilder setDaemon(boolean daemon) {
		this.daemon = daemon;
		return this;
	}

	/**
	 * Get if this thread is a daemon
	 * 
	 * @return the thread builder instance
	 */
	public Boolean getDaemon() {
		return daemon;
	}

	/**
	 * Set the thread priority
	 * 
	 * @param priority
	 *            set thread priority
	 * @return the thread priority
	 */
	public ThreadBuilder setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	/**
	 * Get the threads priority
	 * 
	 * @return the threads priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Set the exception handler for the threads
	 * 
	 * @param uncaughtExceptionHandler
	 *            the exception handler
	 * @return the thread builder instance
	 */
	public ThreadBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
		return this;
	}

	/**
	 * Add an {@linkplain UncaughtExceptionHandler} for the threads
	 * 
	 * @param logger
	 *            the logger to log exceptions
	 * @param loglevel
	 *            a function to know the loglevel that correspond to a throwable (ex: you can say that when the throwable class name is a NPE then the loglevel is error)
	 * @return the builder
	 */
	public ThreadBuilder handleException(Logger logger, BiFunction<Thread, Throwable, Level> loglevel) {
		setUncaughtExceptionHandler((t, e) -> logger.log(loglevel.apply(t, e), e, "Exception [" + e.getClass().getName() + "] in task submitted from thread [" + t.getName() + "] here:"));
		return this;
	}

	/**
	 * Add an {@linkplain UncaughtExceptionHandler} for the threads (the default log level is {@linkplain Level#ERROR})
	 * 
	 * @param logger
	 *            the logger to log exceptions
	 * @return the builder
	 */
	public ThreadBuilder handleException(Logger logger) {
		return handleException(logger, (a, b) -> Level.ERROR);
	}

	/**
	 * Get the threads uncaught exception
	 * 
	 * @return the threads uncaught exception
	 */
	public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return uncaughtExceptionHandler;
	}

	/**
	 * Build the thread
	 * 
	 * @return a thread
	 * @see #build(Runnable)
	 */
	@Override
	public Thread build() {
		return build(runnable);
	}

	/**
	 * Build the thread from the runnable in argument
	 * 
	 * @return a thread
	 */
	private Thread build(Runnable runnable) {
		Thread thread = new Thread(runnable);
		if (name != null) thread.setName(String.format(name, counter.getAndIncrement()));
		if (daemon != null) thread.setDaemon(daemon);
		if (priority != null) thread.setPriority(priority);
		if (uncaughtExceptionHandler != null) thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		return thread;
	}

	/**
	 * Convert this builder to a thread
	 * 
	 * @return a builder converted to a thread
	 *
	 */
	public ThreadFactory toFactory() {
		return this::build;
	}
}
