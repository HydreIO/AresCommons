package fr.aresrpg.commons.domain.util.schedule;

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

	public long convert(long duration, TimeUnit unit) {
		if (unit == this)
			return duration;
		else return duration * this.nanoSeconds / unit.nanoSeconds;
	}

	public long convertFrom(long time, TimeUnit unit) {
		return unit.convert(time, this);
	}

	public long toNanos(long duration) {
		return convert(duration, NANOSECONDS);
	}

	public long toMicros(long duration) {
		return convert(duration, MICROSECONDS);
	}

	public long toMillis(long duration) {
		return convert(duration, MILLISECONDS);
	}

	public long toTicks(long duration) {
		return convert(duration, TICKS);
	}

	public long toSeconds(long duration) {
		return convert(duration, SECONDS);
	}

	public long toMinutes(long duration) {
		return convert(duration, MINUTES);
	}

	public long toHours(long duration) {
		return convert(duration, HOURS);
	}

	public long toDays(long duration) {
		return convert(duration, DAYS);
	}
}
