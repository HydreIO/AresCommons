package fr.aresrpg.commons.domain.event;

/**
 * A event that can be sent in a {@link EventBus}
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Event<T extends Event<T>> {
	/**
	 * Get the bus of this event
	 * 
	 * @return the bus
	 */
	EventBus<T> getBus();

	/**
	 * An asynchronous event will automatically be called on another thread
	 * 
	 * @return if the event is asynchronous
	 */
	boolean isAsynchronous();

	/**
	 * Send this event to his bus
	 */
	@SuppressWarnings("unchecked")
	default void send() {
		if (getBus().subscribersSize() != 0) {
			if (isAsynchronous()) EventBus.EXECUTOR.execute(() -> getBus().send((T) this));
			else getBus().send((T) this);
		}
	}

}