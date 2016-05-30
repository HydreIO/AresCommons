package fr.aresrpg.commons.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import fr.aresrpg.commons.condition.Option;
import fr.aresrpg.commons.util.Enums;

public class Pool {

	public static final Pool COMMON_POOL = create(builder().setName("[AresCommons][COMMON_POOL][THRD:%1%]").setType(PoolType.CACHED).toService(Option.none()));
	private ExecutorService executor;
	private ScheduledExecutorService scheduled;

	private Pool(ExecutorService s1, ScheduledExecutorService s2) {
		this.executor = s1;
		this.scheduled = s2;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public ScheduledExecutorService getScheduled() {
		return scheduled;
	}

	public static PoolBuilder builder() {
		return new PoolBuilder();
	}

	public static Pool create(ExecutorService executor) {
		return new Pool(executor, null);
	}

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

		public PoolBuilder setType(PoolType type) {
			this.type = type;
			return this;
		}

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
		 * @return the builder
		 */
		public PoolBuilder setFactory(ThreadFactory factory) {
			if (this.security) throw new IllegalAccessError("You cant setFactory twice or call setName too");
			this.security = true;
			this.factory = factory;
			return this;
		}

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

		public ScheduledExecutorService toScheduledService(Option<Integer> parallelism) {
			Enums.requireType(this.type, PoolType.SCHEDULED);
			return Executors.newScheduledThreadPool(parallelism.orElse(1), factory);
		}

	}

	public enum PoolType {
		FIXED,
		CACHED,
		SCHEDULED,
		WORK_STEALING
	}

}
