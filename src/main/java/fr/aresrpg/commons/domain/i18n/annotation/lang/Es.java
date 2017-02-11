package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * A lang annotation for the Spanish language
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@LangAnnotation(language = "es")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Es {
	public static final Locale LOCALE = new Locale("es");

	/**
	 * The message in Spanish
	 * 
	 * @return The message in Spanish
	 */
	String value();

}
