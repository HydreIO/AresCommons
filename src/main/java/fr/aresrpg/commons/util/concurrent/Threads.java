package fr.aresrpg.commons.util.concurrent;

import java.util.concurrent.TimeUnit;

public final class Threads {

	public static void sleep(int i, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(i));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
