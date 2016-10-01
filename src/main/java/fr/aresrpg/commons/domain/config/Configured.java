package fr.aresrpg.commons.domain.config;

/**
 * A configured field in {@link Config}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
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
