package fr.aresrpg.commons.domain.concurrent;

import fr.aresrpg.commons.domain.condition.Option;
import fr.aresrpg.commons.domain.util.Enums;

import java.util.concurrent.*;

/**
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Pool {

	public static final Pool COMMON_POOL = create(builder().setName("[AresCommons][COMMON_POOL][THRD:%1%]").setType(PoolType.CACHED).toService(Option.none()));
	private ExecutorService executor;
	private ScheduledExecutorService scheduled;

	private Pool(ExecutorService s1, ScheduledExecutorService s2) {
		this.executor = s1;
		this.scheduled = s2;
	}

	/**
	 * Get the main executor service
	 * 
	 * @return the executor service
	 */
	public ExecutorService getExecutor() {
		return executor;
	}

	/**
	 * Get the main scheduled executor service
	 * 
	 * @return the scheduled executor service
	 */
	public ScheduledExecutorService getScheduled() {
		return scheduled;
	}

	/**
	 * Create a new PoolBuilder
	 * 
	 * @return a PoolBuilder
	 */
	public static PoolBuilder builder() {
		return new PoolBuilder();
	}

	/**
	 * Create a Pool with an executor service
	 * 
	 * @param executor
	 *            the executor to use
	 * @return a Pool instance
	 */
	public static Pool create(ExecutorService executor) {
		return new Pool(executor, null);
	}

	/**
	 * Create a Pool with an scheduled executor service
	 * 
	 * @param scheduled
	 *            the scheduled executor to use
	 * @return a Pool instance
	 */
	public static Pool create(ScheduledExecutorService scheduled) {
		return new Pool(null, scheduled);
	}

	public static class PoolBuilder {
		private PoolType type;
		private ThreadFactory factory;
		private boolean security = false;

		private PoolBuilder() {
			type = PoolType.FIXED;
			factory = Executors.defaultThreadFactory();
		}

		/**
		 * Set the type of the pool
		 * 
		 * @param type
		 *            the type for the pool
		 * @return the builder
		 */
		public PoolBuilder setType(PoolType type) {
			this.type = type;
			return this;
		}

		/**
		 * Set the name for the this thread pool
		 * 
		 * @param name
		 *            name for this pool
		 * @return the builder
		 * @see ThreadBuilder#setName(String)
		 */
		public PoolBuilder setName(String name) {
			if (this.security) throw new IllegalAccessError("You cant setName twice or call setFactory too");
			this.security = true;
			this.factory = new ThreadBuilder().setName(name).toFactory();
			return this;
		}

		/**
		 * For only change the name use {@link PoolBuilder#setName(String)} instead
		 * 
		 * @param factory
		 *            the factory to use
		 * @return the builder
		 */
		public PoolBuilder setFactory(ThreadFactory factory) {
			if (this.security) throw new IllegalAccessError("You cant setFactory twice or call setName too");
			this.security = true;
			this.factory = factory;
			return this;
		}

		/**
		 * Create a new executor service with the provided number of thread
		 * 
		 * @param parallelism
		 *            number of threads to use
		 * @return the executor service created
		 */
		public ExecutorService toService(Option<Integer> parallelism) {
			Enums.requireTypeOr(this.type, PoolType.CACHED, PoolType.FIXED, PoolType.WORK_STEALING);
			switch (this.type) {
				case FIXED:
					return Executors.newFixedThreadPool(parallelism.orElse(1), factory);
				case CACHED:
					return Executors.newCachedThreadPool(factory);
				case WORK_STEALING:
					return Executors.newWorkStealingPool(parallelism.orElse(Runtime.getRuntime().availableProcessors()));
				default:
					return null;
			}
		}

		/**
		 * Create a new scheduled thread pool
		 * 
		 * @param parralelism
		 *            number of threads to use
		 * @return the scheduled executor service
		 */
		public ScheduledExecutorService toScheduled(Option<Integer> parralelism) {
			Enums.requireTypeOr(this.type, PoolType.SCHEDULED);
			return Executors.newScheduledThreadPool(parralelism.orElse(1), factory);
		}

	}

	public enum PoolType {
		FIXED,
		CACHED,
		SCHEDULED,
		WORK_STEALING
	}

}
