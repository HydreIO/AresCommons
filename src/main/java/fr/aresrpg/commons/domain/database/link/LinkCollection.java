package fr.aresrpg.commons.domain.database.link;

import java.lang.annotation.*;

/**
 * An database link
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LinkCollection {
	/**
	 * collection name of the link field
	 * 
	 * @return collection name of this link
	 */
	String value();
}
