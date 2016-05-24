package fr.aresrpg.commons.i18n;

import java.util.Locale;

public interface I18N {


	<T extends L10N> T createL10N(Locale locale, Class<T> clazz);

	static <T extends L10N> T getL10N(Locale locale, Class<T> clazz) {
		if(L10N.class.isAssignableFrom(clazz))
			throw new IllegalArgumentException("clazz is not an L10N instance");
		if(!clazz.isInterface())
			throw new IllegalArgumentException("clazz is not an L10N instance");
		return null;
	}

}
