package fr.aresrpg.commons.domain.util.schedule;

/**
 * A enum containing different time units with util method to do conversions
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public enum TimeUnit {
	NANOSECONDS(1L),
	MICROSECONDS(1000L),
	MILLISECONDS(1000000L),
	SECONDS(1000000000L),
	TICKS(50000000L),
	MINUTES(60000000000L),
	HOURS(3600000000000L),
	DAYS(86400000000000L);

	private final long nanoSeconds;

	TimeUnit(long nanoSeconds) {
		this.nanoSeconds = nanoSeconds;
	}

	/**
	 * Convert the duration to an another unit
	 * @param duration the duration to convert
	 * @param unit the unit to convert to
	 * @return the converted duration
	 */
	public long convert(long duration, TimeUnit unit) {
		if (unit == this)
			return duration;
		else return duration * this.nanoSeconds / unit.nanoSeconds;
	}

	/**
	 * Convert the duration from the provided to this unit
	 * @param time the duration to convert
	 * @param unit the unit to convert from
	 * @return the converted duration
	 * @see #convert(long, TimeUnit)
	 */
	public long convertFrom(long time, TimeUnit unit) {
		return unit.convert(time, this);
	}

	/**
	 * Convert the duration to nanos equivalent
	 * @param duration the duration to convert
	 * @return the duration in nanos
	 */
	public long toNanos(long duration) {
		return convert(duration, NANOSECONDS);
	}

	/**
	 * Convert the duration to nanos equivalent
	 * @param duration the duration to convert
	 * @return the duration in nanos
	 */
	public long toMicros(long duration) {
		return convert(duration, MICROSECONDS);
	}

	/**
	 * Convert the duration to nanos equivalent
	 * @param duration the duration to convert
	 * @return the duration in nanos
	 */
	public long toMillis(long duration) {
		return convert(duration, MILLISECONDS);
	}

	/**
	 * Convert the duration to nanos equivalent
	 * @param duration the duration to convert
	 * @return the duration in nanos
	 */
	public long toTicks(long duration) {
		return convert(duration, TICKS);
	}

	/**
	 * Convert the duration to nanos equivalent
	 * @param duration the duration to convert
	 * @return the duration in nanos
	 */
	public long toSeconds(long duration) {
		return convert(duration, SECONDS);
	}

	/**
	 * Convert the duration to minutes equivalent
	 * @param duration the duration to convert
	 * @return the duration in minutes
	 */
	public long toMinutes(long duration) {
		return convert(duration, MINUTES);
	}

	/**
	 * Convert the duration to hours equivalent
	 * @param duration the duration to convert
	 * @return the duration in hours
	 */
	public long toHours(long duration) {
		return convert(duration, HOURS);
	}

	/**
	 * Convert the duration to days equivalent
	 * @param duration the duration to convert
	 * @return the duration in days
	 */
	public long toDays(long duration) {
		return convert(duration, DAYS);
	}
}
