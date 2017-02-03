package fr.aresrpg.commons.domain.event;

import fr.aresrpg.commons.domain.functional.consumer.BiConsumer;
import fr.aresrpg.commons.domain.functional.consumer.Consumer;
import fr.aresrpg.commons.domain.unsafe.UnsafeAccessor;
import fr.aresrpg.commons.domain.util.Consumers;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

/**
 * A event bus that dispatch event to subscribers
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@SuppressWarnings("rawtypes")
public class EventBus<E> {
	private static final Map<Class<?>, EventBus<?>> buses = new HashMap<>();
	public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(40);
	public static final Comparator<Subscriber> PRIORITY_COMPARATOR = (s1, s2) -> Integer.compare(s1.getPriority(), s2.getPriority());

	private final PriorityBlockingQueue<Subscriber<E>> subscribers;
	private final Class<E> owner;

	/**
	 * Create a new event bus for the provided owner
	 * 
	 * @param owner
	 *            the owner of the bus
	 */
	public EventBus(Class<E> owner) {
		registerBus(owner, this);
		this.owner = owner;
		this.subscribers = new PriorityBlockingQueue<>(11, PRIORITY_COMPARATOR);
	}

	/**
	 * Send an object in this bus
	 * 
	 * @param event
	 *            the object to send
	 */
	public void send(E event) {
		for (Subscriber<E> subscriber : subscribers)
			subscriber.getConsumer().accept(event);
	}

	/**
	 * Subscribe to this bus using a consumer
	 * 
	 * @param consumer
	 *            the consumer to consume the event
	 * @param priority
	 *            the priority of this consumer
	 * @return a Subscriber instance to use with {@link #unsubscribe(Subscriber)}
	 */
	public Subscriber<E> subscribe(Consumer<E> consumer, int priority) {
		Subscriber<E> subscriber = new Subscriber<>(consumer, priority, owner);
		this.subscribers.add(subscriber);
		return subscriber;
	}

	/**
	 * Subscribe to this bus using a consumer
	 * 
	 * @param consumer
	 *            the consumer to consume the event
	 * @param priority
	 *            the priority of this consumer
	 * @return a Subscriber instance to use with {@link #unsubscribe(Subscriber)}
	 */
	public Subscriber<E> subscribe(Consumer<E> consumer) {
		Subscriber<E> subscriber = new Subscriber<>(consumer, 0, owner);
		this.subscribers.add(subscriber);
		return subscriber;
	}

	/**
	 * Subscribe to this bus using a method transformed to a lambda using {@link LambdaMetafactory}
	 * 
	 * @param method
	 *            the method to register
	 * @param instance
	 *            the instance of the method owner
	 * @param priority
	 *            the priority for this method
	 * @return a Subscriber instance to use with {@link #unsubscribe(Subscriber)}
	 * @throws Exception
	 *             if an error occurred during the conversion to a lambda
	 */
	public Subscriber<E> subscribeMethod(Method method, Object instance, int priority) throws Exception {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		return subscribeMethod(lookup, lookup.unreflect(method), instance, priority);
	}

	/**
	 * Subscribe to this bus using a method transformed to a lambda using {@link LambdaMetafactory}
	 * 
	 * @param lookup
	 *            the method lookup to use
	 * @param method
	 *            the method to register
	 * @param instance
	 *            the instance of the method owner
	 * @param priority
	 *            the priority for this method
	 * @return a Subscriber instance to use with {@link #unsubscribe(Subscriber)}
	 * @throws Exception
	 *             if an error occurred during the conversion to a lambda
	 */
	@SuppressWarnings("unchecked")
	public Subscriber<E> subscribeMethod(MethodHandles.Lookup lookup, MethodHandle method, Object instance, int priority) throws Exception {
		try {
			if (instance == null) return subscribe((Consumer<E>) LambdaMetafactory
					.metafactory(lookup, "accept", MethodType.methodType(Consumer.class), MethodType.methodType(void.class, Object.class), method, MethodType.methodType(void.class, owner)).getTarget()
					.invoke(), priority);

			else return subscribe(
					Consumers.from(
							(BiConsumer<Object, E>) LambdaMetafactory.metafactory(lookup, "accept", MethodType.methodType(BiConsumer.class),
									MethodType.methodType(void.class, Object.class, Object.class), method, MethodType.methodType(void.class, instance.getClass(), owner)).getTarget().invoke(),
							instance),
					priority);
		} catch (Throwable e) { // NOSONAR
			throw new ReflectiveOperationException(e);
		}
	}

	/**
	 * Unregister the provided subscriber
	 * 
	 * @param subscriber
	 *            the subscriber to remove
	 */
	public void unsubscribe(Subscriber<E> subscriber) {
		this.subscribers.remove(subscriber);
	}

	/**
	 * Get all the subscribers of this bus
	 * 
	 * @return an immutable collection of the subscribers of this bus
	 */
	public Collection<Subscriber<E>> getSubscribers() {
		return Collections.unmodifiableCollection(subscribers);
	}

	/**
	 * Get the number of subscribers in this bus
	 * 
	 * @return the number of subscribers
	 */
	public int subscribersSize() {
		return subscribers.size();
	}

	/**
	 * Register this bus to listen it
	 * 
	 * @param owner
	 *            the owner of the bus
	 * @param bus
	 *            the bus instance
	 */
	private static void registerBus(Class<?> owner, EventBus<?> bus) {
		if (buses.putIfAbsent(owner, bus) == null) new BusRegisterEvent(owner, bus).send();
	}

	/**
	 * Get the registered bus with the specified owner
	 * 
	 * @param owner
	 *            the owner of the bus
	 * @param <T>
	 *            the type of the event
	 * @return the event bus or null if not found
	 */
	@SuppressWarnings("unchecked")
	public static <T> EventBus<T> getBus(Class<T> owner) {
		UnsafeAccessor.getUnsafe().ensureClassInitialized(owner);
		return (EventBus<T>) buses.get(owner);
	}

	public static <T> void unsubscribing(Subscriber<T> sub) {
		getBus(sub.getClazz()).unsubscribe(sub);
	}

	/**
	 * Get all registered buses
	 * 
	 * @return a immutable map of all registered buses
	 */
	public static Map<Class<?>, EventBus<?>> getBuses() {
		return Collections.unmodifiableMap(buses);
	}

}
