package fr.aresrpg.commons.domain.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * An util class to use with threads
 *
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public final class Threads {

	private Threads() {
	}

	/**
	 * <p>
	 * Switch the name of a thread before a submission, useful to know exactly what a thread is actually processing and not spend hours while debuging
	 * </p>
	 * The old name is replaced after execution.
	 * <br>
	 * Example of use:
	 * <p>
	 * 
	 * <pre>
	 * {@literal CompletableFutur.supplyAsync(threadContextSwitch("Processing-$myResultName"), () -> myResult);}
	 * </pre>
	 *
	 * @param <T>
	 *            the type input type
	 * @param <U>
	 *            the output type
	 * @param newname
	 *            the new name to indicate what the thread is currently doing
	 * @param logic
	 *            the function to get your result
	 * @return your function wrapped in another function which is changing the name of the thread and replacing it after the execution
	 */
	public static <T, U> Function<T, U> threadContextSwitch(String newname, Function<T, U> logic) {
		return i -> {
			final Thread currentThread = Thread.currentThread();
			final String oldName = currentThread.getName();
			currentThread.setName(newname);
			try {
				return logic.apply(i);
			} finally {
				currentThread.setName(oldName);
			}
		};
	}

	/**
	 * <p>
	 * Switch the name of a thread before a submission, useful to know exactly what a thread is actually processing and not spend hours while debuging
	 * </p>
	 * The old name is replaced after execution.
	 * <br>
	 * Example of use:
	 * <p>
	 * 
	 * <pre>
	 * {@literal CompletableFutur.supplyAsync(threadContextSwitch("Processing-$myResultName"), () -> myResult);}
	 * </pre>
	 *
	 * @param <T>
	 *            the type of your Consumer
	 * @param newname
	 *            the new name to indicate what the thread is currently doing
	 * @param logic
	 *            the Consumer to get your result
	 * @return your Consumer wrapped in another Consumer which is changing the name of the thread and replacing it after the execution
	 */
	public static <T> Consumer<T> threadContextSwitch(String newname, Consumer<T> logic) {
		return a -> {
			final Thread currentThread = Thread.currentThread();
			final String oldName = currentThread.getName();
			currentThread.setName(newname);
			try {
				logic.accept(a);
			} finally {
				currentThread.setName(oldName);
			}
		};
	}

	/**
	 * <p>
	 * Switch the name of a thread before a submission, useful to know exactly what a thread is actually processing and not spend hours while debuging
	 * </p>
	 * The old name is replaced after execution.
	 * <br>
	 * Example of use:
	 * <p>
	 * 
	 * <pre>
	 * {@literal CompletableFutur.supplyAsync(threadContextSwitch("Processing-$myResultName"), () -> myResult);}
	 * </pre>
	 *
	 * @param <T>
	 *            the type of your supplier
	 * @param newname
	 *            the new name to indicate what the thread is currently doing
	 * @param logic
	 *            the supplier to get your result
	 * @return your supplier wrapped in another supplier which is changing the name of the thread and replacing it after the execution
	 */
	public static <T> Supplier<T> threadContextSwitch(String newname, Supplier<T> logic) {
		return () -> {
			final Thread currentThread = Thread.currentThread();
			final String oldName = currentThread.getName();
			currentThread.setName(newname);
			try {
				return logic.get();
			} finally {
				currentThread.setName(oldName);
			}
		};
	}

	/**
	 * <p>
	 * Switch the name of a thread before a submission, useful to know exactly what a thread is actually processing and not spend hours while debuging
	 * </p>
	 * The old name is replaced after execution.
	 * <br>
	 * Example of use:
	 * <p>
	 * 
	 * <pre>
	 * {@literal CompletableFutur.supplyAsync(threadContextSwitch("Processing-$myResultName"), () -> myResult);}
	 * </pre>
	 *
	 * @param newname
	 *            the new name to indicate what the thread is currently doing
	 * @param logic
	 *            the supplier to get your result
	 * @return your supplier wrapped in another supplier which is changing the name of the thread and replacing it after the execution
	 */
	public static Runnable threadContextSwitch(String newname, Runnable logic) {
		return () -> {
			final Thread currentThread = Thread.currentThread();
			final String oldName = currentThread.getName();
			currentThread.setName(newname);
			try {
				logic.run();
			} finally {
				currentThread.setName(oldName);
			}
		};
	}

	/**
	 * Sleep the thread
	 *
	 * @param value
	 *            the time to sleep
	 * @param unit
	 *            the unit for the time
	 * @throws InterruptedException
	 *             if the sleep fail
	 */
	public static void sleep(int value, TimeUnit unit) throws InterruptedException {
		Thread.sleep(unit.toMillis(value));
	}

	public static void uSleep(long millis) {
		uSleep(millis, TimeUnit.MILLISECONDS);
	}

	/**
	 * Sleep the thread or interrupt if it's fail
	 *
	 * @param value
	 *            the time to sleep
	 * @param unit
	 *            the unit for the time
	 */
	public static void uSleep(long value, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(value));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
