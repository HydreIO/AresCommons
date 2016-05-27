package fr.aresrpg.commons.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import fr.aresrpg.commons.builder.Builder;

public class ThreadBuilder implements Builder<Thread> {

	private Runnable runnable;
	private String name;
	private Boolean daemon;
	private Integer priority;
	private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

	public ThreadBuilder setRunnable(Runnable runnable) {
		this.runnable = runnable;
		return this;
	}

	public Runnable getRunnable() {
		return runnable;
	}

	/**
	 * Set %1% to get the number of the thread
	 * 
	 * @param name
	 *            the thread name
	 * @return the builder
	 */
	public ThreadBuilder setName(String name) {
		Objects.requireNonNull(name);
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public ThreadBuilder setDaemon(boolean daemon) {
		this.daemon = daemon;
		return this;
	}

	public Boolean getDaemon() {
		return daemon;
	}

	public ThreadBuilder setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	public Integer getPriority() {
		return priority;
	}

	public ThreadBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
		return this;
	}

	public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return uncaughtExceptionHandler;
	}

	@Override
	public Thread build() {
		return build(runnable);
	}

	private Thread build(Runnable runnable) {
		Thread thread = new Thread(runnable);
		if (name != null) thread.setName(name);
		if (daemon != null) thread.setDaemon(daemon);
		if (priority != null) thread.setPriority(priority);
		if (uncaughtExceptionHandler != null) thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		return thread;
	}

	public ThreadFactory toFactory() {
		return new ThreadFactory() {
			AtomicInteger counter = new AtomicInteger(0);

			@Override
			public Thread newThread(Runnable runnable) {
				Thread thread = build(runnable);
				if (name != null) thread.setName(String.format(name, counter.getAndIncrement()));
				return thread;
			}
		};
	}
}
