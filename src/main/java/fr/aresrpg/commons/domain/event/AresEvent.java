package fr.aresrpg.commons.domain.event;

import java.lang.annotation.*;

/**
 * Represent a method that is a {@link Event} consumer
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AresEvent {

	/**
	 * Get the priority of this event
	 * 
	 * @return the priority
	 */
	int priority() default 0;

}
