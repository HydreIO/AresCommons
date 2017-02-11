package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * A lang annotation for the Arabic language
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@LangAnnotation(language = "ar")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ar {
	public static final Locale LOCALE = new Locale("ar");

	/**
	 * The message in Arabic
	 * 
	 * @return The message in Arabic
	 */
	String value();

}
