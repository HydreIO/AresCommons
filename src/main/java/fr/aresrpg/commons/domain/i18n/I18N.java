package fr.aresrpg.commons.domain.i18n;

import fr.aresrpg.commons.infra.i18n.ASMPropertiesI18N;

import java.util.Locale;

/**
 * A class to deal with localization
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface I18N {
	/**
	 * A class to hold the implementation
	 */
	class Impl {
		private static final I18N IMPLEMENTATION = new ASMPropertiesI18N();

		private Impl() {
		}
	}

	/**
	 * Create or get a new L10N bundle for the specified locale from the bundle class
	 * 
	 * @param locale
	 *            the locale to create bundle
	 * @param clazz
	 *            the class of the bundle
	 * @param <T>
	 *            the type of the class
	 * @return a instance of the class generated with the specified locale
	 */
	<T extends L10N> T createL10N(Locale locale, Class<T> clazz);

	/**
	 * Create or get a new L10N bundle for the specified locale from the bundle class
	 * 
	 * @param locale
	 *            the locale to create bundle
	 * @param clazz
	 *            the class of the bundle
	 * @param <T>
	 *            the type of the class
	 * @return a instance of the class with the specified locale
	 */
	static <T extends L10N> T getL10N(Locale locale, Class<T> clazz) {
		return Impl.IMPLEMENTATION.createL10N(locale, clazz);
	}

}
