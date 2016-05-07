package fr.aresrpg.commons.i18n.annotation.lang;

import fr.aresrpg.commons.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

@LangAnnotation(language = "ja")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Ja {
	String value();
}
