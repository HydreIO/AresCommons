package fr.aresrpg.commons.domain.i18n.annotation.lang;

import fr.aresrpg.commons.domain.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

@LangAnnotation(language = "ja")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ja {
	String value();
}
