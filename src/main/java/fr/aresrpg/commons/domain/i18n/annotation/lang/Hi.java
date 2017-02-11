package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * A lang annotation for the Hindi language
 * 
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@LangAnnotation(language = "hi")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Hi {
	public static final Locale LOCALE = new Locale("hi");

	/**
	 * The message in Hindi
	 * 
	 * @return The message in Hindi
	 */
	String value();

}
