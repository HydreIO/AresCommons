package fr.aresrpg.commons.domain.i18n.annotation;

import java.lang.annotation.*;

/**
 * A annotation to specify that the current field specify the plural
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface PluralCount {

}
