package fr.aresrpg.commons.domain.event;

import java.lang.reflect.Method;

/**
 * An util class for events
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public class Events {
	private Events() {}

	/**
	 * Register all {@link AresEvent} in the provided object
	 * @param listener the instance to register
	 * @throws IllegalArgumentException if the methods are not consumers
	 * @throws Exception if an exception from the registering occurred
	 */
	@SuppressWarnings({ "unchecked" })
	public static void register(Listener listener) throws Exception {
		for (Method m : listener.getClass().getDeclaredMethods()) {
			AresEvent event = m.getAnnotation(AresEvent.class);
			if (event == null)
				continue;
			if (m.getParameterCount() != 1)
				throw new IllegalArgumentException("The method " + m.toGenericString() + " must have only one parameter");
			if (!m.getParameterTypes()[0].isAssignableFrom(Event.class))
				throw new IllegalArgumentException("The parameter of the method " + m.toGenericString() + " must extend Event.class");

			EventBus.getBus((Class<Event<?>>) m.getParameterTypes()[0]).subscribeMethod(m, listener, event.priority());
		}
	}

}
