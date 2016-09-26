package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

/**
 * A lang annotation for the Italian language
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@LangAnnotation(language = "it")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface It {
	/**
	 * The message in Italian
	 * 
	 * @return The message in Italian
	 */
	String value();
}
