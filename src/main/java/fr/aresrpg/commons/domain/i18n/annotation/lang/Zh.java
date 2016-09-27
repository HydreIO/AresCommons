package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

/**
 * A lang annotation for the language
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@LangAnnotation(language = "zh")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Zh {
	String value();
}
