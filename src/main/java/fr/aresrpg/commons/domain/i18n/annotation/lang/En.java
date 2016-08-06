package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

/**
 * A lang annotation for the English language
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@LangAnnotation(language = "en")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface En {
	/**
	 * The message in English
	 * @return The message in English
	 */
	String value();
}
