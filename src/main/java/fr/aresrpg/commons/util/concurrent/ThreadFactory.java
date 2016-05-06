package fr.aresrpg.commons.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

@FunctionalInterface
public interface ThreadFactory {

	String named();

	default java.util.concurrent.ThreadFactory build(boolean daemon) {
		return new java.util.concurrent.ThreadFactory() {
			final AtomicInteger count = new AtomicInteger(0);

			@Override
			public Thread newThread(Runnable r) {
				String name = named();
				int var = count.incrementAndGet();
				if (!name.contains("$d")) name += " $d";
				Thread thread = new Thread(r, name.replace("$d", String.valueOf(var)));
				if (daemon) thread.setDaemon(true);
				return thread;
			}
		};
	}

	/**
	 * Build un threadFactory basique avec juste un rename des thread ! Mettre $d pour le num du thread
	 * 
	 * @param pattern
	 * @return
	 */
	public static ThreadFactory create(String pattern) {
		return () -> pattern;
	}

}
