package fr.aresrpg.commons.i18n.annotation.lang;

import fr.aresrpg.commons.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

@LangAnnotation(language = "it")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface It {
	String value();
}
