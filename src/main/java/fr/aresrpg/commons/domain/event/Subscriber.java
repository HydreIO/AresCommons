package fr.aresrpg.commons.domain.event;


import fr.aresrpg.commons.domain.condition.functional.consumer.Consumer;

public class Subscriber<E> {
	private Consumer<E> consumer;
	private int priority;

	public Subscriber(Consumer<E> consumer, int priority) {
		this.consumer = consumer;
		this.priority = priority;
	}

	public Consumer<E> getConsumer() {
		return consumer;
	}

	public int getPriority() {
		return priority;
	}
}
