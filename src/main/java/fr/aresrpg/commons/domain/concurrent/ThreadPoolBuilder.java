package fr.aresrpg.commons.domain.concurrent;

import fr.aresrpg.commons.domain.util.Enums;

import java.util.concurrent.*;

/**
 * A thread pool builder
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class ThreadPoolBuilder {
	private ThreadBuilder threadbuilder;
	private PoolType type;
	private int parallelism;

	public ThreadPoolBuilder(ThreadBuilder builder) {
		type = PoolType.FIXED;
		parallelism = Runtime.getRuntime().availableProcessors();
		if (builder.getName() == null) builder.setName("UNNAMED_POOL[thrd:%1$]");
	}

	/**
	 * Set the type of the pool
	 * 
	 * @param type
	 *            the type for the pool
	 * @return the builder
	 */
	public ThreadPoolBuilder setType(PoolType type) {
		this.type = type;
		return this;
	}

	public ThreadPoolBuilder setParallelism(int parallelism) {
		this.parallelism = parallelism;
		return this;
	}

	/**
	 * Build the service as an ExecutorService
	 * 
	 * @return the executor service created
	 */
	public ExecutorService buildAsService() {
		Enums.requireTypeOr(this.type, PoolType.CACHED, PoolType.FIXED, PoolType.WORK_STEALING);
		switch (this.type) {
			case FIXED:
				return Executors.newFixedThreadPool(parallelism, threadbuilder.toFactory());
			case WORK_STEALING:
				return Executors.newWorkStealingPool(parallelism);
			default:
				return Executors.newCachedThreadPool(threadbuilder.toFactory());
		}
	}

	/**
	 * Build the service as a ScheduledExecutorService
	 * 
	 * @return the scheduled executor service
	 */
	public ScheduledExecutorService buildAsScheduled() {
		Enums.requireTypeOr(this.type, PoolType.SCHEDULED);
		return Executors.newScheduledThreadPool(parallelism, threadbuilder.toFactory());
	}

	public enum PoolType {
		FIXED,
		CACHED,
		SCHEDULED,
		WORK_STEALING
	}
}
