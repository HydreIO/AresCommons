package fr.aresrpg.commons.domain.util.schedule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that this method must be scheduled
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Schedule {
	/**
	 * Provide the rate between successive executions of this scheduled method
	 * @return The rate between successive executions
	 */
	int rate();

	/**
	 * The time unit of the rate method
	 * @return the unit of the rate
	 */
	TimeUnit unit();
}
