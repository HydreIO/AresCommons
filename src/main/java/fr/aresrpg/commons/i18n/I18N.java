package fr.aresrpg.commons.i18n;

import java.util.Locale;

@FunctionalInterface
public interface I18N {
	class Impl{
		private static final I18N IMPLEMENTATION = new ASMPropertiesI18N();
		private Impl(){}
	}

	<T extends L10N> T createL10N(Locale locale, Class<T> clazz);

	static <T extends L10N> T getL10N(Locale locale, Class<T> clazz) {
		return Impl.IMPLEMENTATION.createL10N(locale , clazz);
	}

}
