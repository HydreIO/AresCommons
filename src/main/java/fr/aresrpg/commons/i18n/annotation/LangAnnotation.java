package fr.aresrpg.commons.i18n.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Locale;

@Target(ElementType.ANNOTATION_TYPE)
public @interface LangAnnotation {
    String EMPTY = "";


    String language();
    String country() default EMPTY;
    String variant() default EMPTY;
}
