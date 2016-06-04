package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

@LangAnnotation(language = "zh")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Zh {
	String value();
}
