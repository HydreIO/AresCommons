package fr.aresrpg.commons.i18n.annotation.lang;

import fr.aresrpg.commons.i18n.annotation.LangAnnotation;

import java.lang.annotation.*;

@LangAnnotation(language = "zh")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Zh {
	String value();
}
