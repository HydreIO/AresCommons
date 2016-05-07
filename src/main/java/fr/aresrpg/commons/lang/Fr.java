package fr.aresrpg.commons.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Fr {

	String value();

}
