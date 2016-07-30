package fr.aresrpg.commons.domain.util.schedule;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import fr.aresrpg.commons.domain.concurrent.ThreadBuilder;
import fr.aresrpg.commons.domain.log.Logger;

/**
 * The class to launch {@link Scheduled} instance
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class Scheduler {

	private static Scheduler instance = new Scheduler();

	private ScheduledExecutorService pool = Executors.newScheduledThreadPool(50, new ThreadBuilder().setName("Scheduler pool - [Thrd: %1]").toFactory());

	private Scheduler() {}

	/**
	 * Get the scheduler instance
	 * @return the scheduler instance
	 */
	public static Scheduler getScheduler() {
		return instance;
	}


	/**
	 * Shutdown the scheduler , if this method is not called the application will not exit
	 */
	public void shutdown() {
		pool.shutdown();
	}

	/**
	 * Register the  method annotated with {@link Schedule} in {@link Scheduled} instance for scheduling
	 * @param scheduled the scheduled instance to register
	 */
	public void register(Scheduled scheduled) {
		Arrays.stream(scheduled.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Schedule.class)).forEach(m -> {
			Schedule s = m.getAnnotation(Schedule.class);
			long ns = s.unit().toNanos(s.rate());
			pool.scheduleAtFixedRate(() -> {
				try {
					m.invoke(scheduled, new Object[m.getParameterCount()]);
				} catch (Exception e) {
					Logger.MAIN_LOGGER.debug(e , "[Scheduler] Error with " + "//: " + m.getDeclaringClass() + "//:" + m.getName());
				}
			}, ns, ns, java.util.concurrent.TimeUnit.NANOSECONDS);
		});
	}

}
