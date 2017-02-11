package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * A lang annotation for the Russian language
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@LangAnnotation(language = "ru")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ru {
	public static final Locale LOCALE = new Locale("ru");

	/**
	 * The message in Russian
	 * 
	 * @return The message in Russian
	 */
	String value();

}
