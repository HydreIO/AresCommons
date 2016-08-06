package fr.aresrpg.commons.domain.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represent a method that is a {@link Event} consumer
 * @author Cyril Morlet {@literal <mr.sceat@outlook.com>}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AresEvent {

	/**
	 * Get the priority of this event
	 * @return the priority
	 */
	int priority() default 0;

}
