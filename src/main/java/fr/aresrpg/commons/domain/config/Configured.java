package fr.aresrpg.commons.domain.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A configured field in {@link Config}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Configured {
	/**
	 * The path
	 * </p>
	 * Use dot for subpath ex : <code>@Configured(foo.bar.config)</code>
	 * 
	 * @return the path in the configuration
	 */
	String value() default "var.";
}
