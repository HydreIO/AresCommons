package fr.aresrpg.commons.domain.event;

import java.lang.annotation.*;

/**
 * Represent a method that is an {@link Event} consumer
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

	/**
	 * Get the priority of this event
	 * 
	 * @return the priority
	 */
	int priority() default 0;

}
