package fr.aresrpg.commons.domain.serialization.annotations;

import java.lang.annotation.*;

/**
 * A serialized name is used to provide a generic name to a field after his serialization.
 * </p>
 * For example in a database you may want to use a custom field name instead of the native one
 * 
 * <pre>
 * &#64;SerializedName("userId")
 * private long id;
 * </pre>
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SerializedName {
	/**
	 * Name in the collection of this field
	 * 
	 * @return name in the collection of this field
	 */
	String value();
}
