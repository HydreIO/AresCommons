package fr.aresrpg.commons.util.concurrent;

import java.util.concurrent.TimeUnit;

import fr.aresrpg.commons.log.Log;

public interface Thread {

	public static void sleep(int i, TimeUnit unit) {
		try {
			java.lang.Thread.sleep(unit.toMillis(i));
		} catch (InterruptedException e) {
			Log.trace(e);
		}
	}

}
