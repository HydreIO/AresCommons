package fr.aresrpg.commons.domain.database.link;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An database link
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LinkCollection {
    /**
     * collection name of this link field
     * @return collection name of this link
     */
    String value();
}
