package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

/**
 * A lang annotation for the Deutch language
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@LangAnnotation(language = "de")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface De {
	/**
	 * The message in Deutch
	 * @return The message in Deutch
	 */
	String value();
}
