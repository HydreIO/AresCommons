package fr.aresrpg.commons.domain.event;

import fr.aresrpg.commons.domain.functional.consumer.Consumer;

/**
 * A subscriber of an {@link EventBus}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class Subscriber<E> {
	private Consumer<E> consumer;
	private int priority;

	/**
	 * Create a new subscriber
	 * @param consumer the consumer of this subscriber
	 * @param priority the priority of this subscriber in the event bus
	 */
	public Subscriber(Consumer<E> consumer, int priority) {
		this.consumer = consumer;
		this.priority = priority;
	}

	/**
	 * Get the consumer of this subscriber
	 * @return the consumer
	 */
	public Consumer<E> getConsumer() {
		return consumer;
	}

	/**
	 * Get the priority of this subscriber
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
}
