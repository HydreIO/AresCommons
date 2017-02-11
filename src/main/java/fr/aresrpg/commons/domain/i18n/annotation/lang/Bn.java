package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * A lang annotation for the Bengali language
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@LangAnnotation(language = "bn")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bn {
	public static final Locale LOCALE = new Locale("bn");

	/**
	 * The message in Bengali
	 * 
	 * @return The message in Bengali
	 */
	String value();

}
