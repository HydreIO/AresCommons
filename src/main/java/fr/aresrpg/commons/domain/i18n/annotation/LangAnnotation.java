package fr.aresrpg.commons.domain.i18n.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A annotation to specify that this annotation is a lang annotation
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LangAnnotation {
	String EMPTY = "";

	/**
	 * Get the language of this lang annotation
	 * @return the language
	 */
	String language();

	/**
	 * Get the country of this lang annotation
	 * @return the country
	 */
	String country() default EMPTY;

	/**
	 * Get the variant of this lang annotation
	 * @return the variant
	 */
	String variant() default EMPTY;
}
