package fr.aresrpg.commons.domain.util.schedule;

import fr.aresrpg.commons.domain.concurrent.ThreadPoolBuilder;
import fr.aresrpg.commons.domain.log.Logger;

import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The class to launch {@link Scheduled} instance
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Scheduler {

	private ScheduledExecutorService pool;
	private int taskCount;

	public Scheduler(ScheduledExecutorService pool) {
		this.pool = pool;
	}

	public Scheduler(ThreadPoolBuilder builder) {
		this.pool = builder.buildAsScheduled();
	}

	/**
	 * The task count represent the number of method or runnable registered in the scheduler
	 * 
	 * @return the taskCount
	 */
	public int getTaskCount() {
		return taskCount;
	}

	/**
	 * Shutdown the scheduler , if this method is not called the application will not exit
	 */
	public void shutdown() {
		pool.shutdown();
	}

	/**
	 * Register a runnable which will be executed every X nano
	 * 
	 * @param runnable
	 *            the runnable
	 * @param nano
	 *            the interval time in nano second
	 */
	public void register(Runnable runnable, long nano) {
		taskCount++;
		pool.scheduleAtFixedRate(runnable, nano, nano, TimeUnit.NANOSECONDS);
	}

	/**
	 * Register a runnable which will be executed every X time
	 * 
	 * @param runnable
	 *            the runnable
	 * @param time
	 *            the interval time
	 * @param unit
	 *            the time unit of the interval time
	 */
	public void register(Runnable runnable, long time, TimeUnit unit) {
		register(runnable, unit.toNanos(time));
	}

	/**
	 * Register all the method with the {@linkplain Schedule} annotation from a {@linkplain Scheduled} class
	 * 
	 * @param scheduled
	 *            the class instance
	 */
	public void register(Scheduled scheduled) {
		Arrays.stream(scheduled.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Schedule.class)).forEach(m -> {
			Schedule s = m.getAnnotation(Schedule.class);
			register(() -> {
				try {
					m.invoke(scheduled, new Object[m.getParameterCount()]);
				} catch (Exception e) {
					// TODO @DeltaEvo
				}
			} , s.unit().toNanos(s.rate()));
		});
	}

	/**
	 * Register the method annotated with {@link Schedule} in {@link Scheduled} instance for scheduling
	 * 
	 * @param scheduled
	 *            the scheduled instance to register
	 * @deprecated
	 */
	@Deprecated
	public void registerOld(Scheduled scheduled) {
		Arrays.stream(scheduled.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Schedule.class)).forEach(m -> {
			Schedule s = m.getAnnotation(Schedule.class);
			long ns = s.unit().toNanos(s.rate());
			taskCount++;
			pool.scheduleAtFixedRate(() -> {
				try {
					m.invoke(scheduled, new Object[m.getParameterCount()]);
				} catch (Exception e) {
					Logger.MAIN_LOGGER.debug(e, "[Scheduler] Error with " + "//: " + m.getDeclaringClass() + "//:" + m.getName());
				}
			} , ns, ns, java.util.concurrent.TimeUnit.NANOSECONDS);
		});
	}

}
