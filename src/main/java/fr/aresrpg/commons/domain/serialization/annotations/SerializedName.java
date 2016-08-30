package fr.aresrpg.commons.domain.serialization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Set the serialized name of the field
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SerializedName {
    /**
     * Name in the collection of this field
     * @return name in the collection of this field
     */
    String value();
}
