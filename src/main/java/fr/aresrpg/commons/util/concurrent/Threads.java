package fr.aresrpg.commons.util.concurrent;

import java.util.concurrent.TimeUnit;

import fr.aresrpg.commons.log.Log;

public final class Threads {

	public static void sleep(int i, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(i));
		} catch (InterruptedException e) {
			Log.trace(e);
		}
	}

}
