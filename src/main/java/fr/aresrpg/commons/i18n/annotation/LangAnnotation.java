package fr.aresrpg.commons.i18n.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LangAnnotation {
	String EMPTY = "";

	String language();

	String country() default EMPTY;

	String variant() default EMPTY;
}
