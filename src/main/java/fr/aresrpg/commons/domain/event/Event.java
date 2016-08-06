package fr.aresrpg.commons.domain.event;

/**
 * A event that can be send in a {@link EventBus}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Event<T extends Event<T>> {
	/**
	 * Get the bus of this event
	 * @return the bus
	 */
	EventBus<T> getBus();

	/**
	 * Get if this event must be call asynchronously
	 * @return if the event is asynchronous
	 */
	boolean isAsynchronous();

	/**
	 * Send this event to the bus
	 */
	@SuppressWarnings("unchecked")
	default void send() {
		if (getBus().subscribersSize() != 0) {
			if (isAsynchronous()) EventBus.EXECUTOR.execute(() -> getBus().send((T) this));
			else getBus().send((T) this);
		}
	}

}