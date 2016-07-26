package fr.aresrpg.commons.domain.event;

import fr.aresrpg.commons.domain.log.Logger;

import java.util.Arrays;

public interface Events {

	@SuppressWarnings({ "unchecked" })
	public static void register(Object c) throws IllegalArgumentException {
		Arrays.stream(c.getClass().getDeclaredMethods()).filter(m -> m.isAnnotationPresent(AresEvent.class)).forEach(m -> {
			if (m.getParameterCount() != 1) throw new IllegalArgumentException("The method " + m.toGenericString() + " must have only one parameter");
			else if (!m.getParameterTypes()[0].isAssignableFrom(Event.class)) throw new IllegalArgumentException("The parameter of the method " + m.toGenericString() + " must extend Event.class");
			try {
				EventBus.getBus((Class<Event<?>>) m.getParameterTypes()[0]).subscribeMethod(m, c, ((AresEvent) m.getAnnotation(AresEvent.class)).priority());
			} catch (Exception e) {
				Logger.MAIN_LOGGER.error(e , "Error while register bus for class {}" , c.getClass());
			}
		});
	}

}
