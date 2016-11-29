package fr.aresrpg.commons.domain.util.schedule;

import fr.aresrpg.commons.domain.concurrent.ThreadPoolBuilder;
import fr.aresrpg.commons.domain.log.Logger;

import java.util.*;
import java.util.concurrent.*;

/**
 * The class to launch {@link Scheduled} instance
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Scheduler {

	private ScheduledExecutorService pool;
	private int taskCount;
	private Map<Integer, ScheduledFuture> tasks = new HashMap<>();

	public Scheduler(ScheduledExecutorService pool) {
		this.pool = pool;
	}

	public Scheduler(ThreadPoolBuilder builder) {
		this.pool = builder.buildAsScheduled();
	}

	/**
	 * Unregister a task
	 * 
	 * @param id
	 *            the id retrived while registering the task
	 * @return true if the task was unregistered, false otherwise
	 */
	public boolean unregister(int id) {
		ScheduledFuture ft = tasks.get(id);
		boolean found = ft != null;
		if (found) ft.cancel(false);
		return found;
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
	 * @return the id of the task
	 */
	public int register(Runnable runnable, long nano) {
		tasks.put(++taskCount, pool.scheduleAtFixedRate(runnable, nano, nano, TimeUnit.NANOSECONDS));
		return taskCount;
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
	 * @return the id of the task
	 */
	public int register(Runnable runnable, long time, TimeUnit unit) {
		return register(runnable, unit.toNanos(time));
	}

	/**
	 * Register all the method with the {@linkplain Schedule} annotation from a {@linkplain Scheduled} class
	 * 
	 * @param scheduled
	 *            the class instance
	 * @return the ids of all task in the scheduled object
	 */
	public List<Integer> register(Scheduled scheduled) {
		return Arrays.stream(scheduled.getClass().getDeclaredMethods()).filter(m -> m.isAnnotationPresent(Schedule.class)).collect(ArrayList::new, (a, m) -> {
			Schedule s = m.getAnnotation(Schedule.class);
			a.add(register(() -> {
				try {
					m.invoke(scheduled, new Object[m.getParameterCount()]);
				} catch (Exception e) {
					Logger.MAIN_LOGGER.debug(e, "Error in scheduled execution");
				}
			} , s.unit().toNanos(s.rate())));
		} , ArrayList::addAll);
	}

}
