package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

/**
 * A lang annotation for the French language
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@LangAnnotation(language = "fr")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Fr {
	/**
	 * The message in French
	 * 
	 * @return The message in French
	 */
	String value();
}
