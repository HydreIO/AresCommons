package fr.aresrpg.commons.event;

import java.util.Arrays;

import fr.aresrpg.commons.log.Logger;

public class Events {

	private Events() {

	}

	@SuppressWarnings({ "unchecked" })
	public static <T extends Event<?>, C> void register(C c, int priority) throws IllegalArgumentException {
		Arrays.stream(c.getClass().getDeclaredMethods())
				/* NOSONAR auto closeable */
				.filter(m -> m.isAnnotationPresent(AresEvent.class))
				.forEach(
						m -> {
							if (m.getParameterCount() != 1) throw new IllegalArgumentException("The method " + m.toGenericString() + " must have only one parameter");
							else if (!m.getParameterTypes()[0].isAssignableFrom(EventBus.class)) throw new IllegalArgumentException("The parameter of the method " + m.toGenericString()
									+ " must extend EventBus.class");
							try {
								EventBus.getBus((Class<T>) m.getParameterTypes()[0]).subscribeMethod(m, c, priority);
							} catch (Exception e) {
								Logger.MAIN_LOGGER.error(e);
							}
						});

	}

}
