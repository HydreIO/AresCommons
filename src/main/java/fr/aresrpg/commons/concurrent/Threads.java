package fr.aresrpg.commons.concurrent;

import java.util.concurrent.TimeUnit;

public final class Threads {

	private Threads(){}

	public static void sleep(int value, TimeUnit unit) throws InterruptedException {
		Thread.sleep(unit.toMillis(value));
	}

}
