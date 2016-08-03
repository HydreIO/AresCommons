package fr.aresrpg.commons.domain.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * An util class to use with threads
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public final class Threads {

	private Threads() {
	}

	/**
	 * Sleep the thread
	 * @param value the time to sleep
	 * @param unit the unit for the time
	 * @throws InterruptedException if the sleep fail
	 */
	public static void sleep(int value, TimeUnit unit) throws InterruptedException {
		Thread.sleep(unit.toMillis(value));
	}

	/**
	 * Sleep the thread or interrupt if it's fail
	 * @param value the time to sleep
	 * @param unit the unit for the time
	 */
	public static void uSleep(int value, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(value));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
