package fr.aresrpg.commons.concurrent;

import java.util.concurrent.TimeUnit;

public final class Threads {

	private Threads() {
	}

	public static void sleep(int value, TimeUnit unit) throws InterruptedException {
		Thread.sleep(unit.toMillis(value));
	}

	public static void uSleep(int value, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(value));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
