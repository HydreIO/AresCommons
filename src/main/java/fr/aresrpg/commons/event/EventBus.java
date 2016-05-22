package fr.aresrpg.commons.event;

import fr.aresrpg.commons.Consumers;
import fr.aresrpg.commons.condition.functional.consumer.BiConsumer;
import fr.aresrpg.commons.condition.functional.consumer.Consumer;
import fr.aresrpg.commons.unsafe.UnsafeAccessor;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus<E extends Event> {
	private static final Map<Class<? extends Event> , EventBus<? extends Event>> buses = new HashMap<>();
	public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
	public static final Comparator<Subscriber> PRIORITY_COMPARATOR = (s1 , s2) -> Integer.compare(s1.getPriority() , s2.getPriority());

	private final PriorityQueue<Subscriber<E>> subscribers;
	private final Class<E> owner;

	public EventBus(Class<E> owner) {
		registerBus(owner , this);
		this.owner = owner;
		this.subscribers = new PriorityQueue<>(PRIORITY_COMPARATOR);
	}

	public void send(E event){
		for(Subscriber<E> subscriber : subscribers)
			subscriber.getConsumer().accept(event);
	}

	public Subscriber<E> subscribe(Consumer<E> consumer , int priority) {
		Subscriber<E> subscriber = new Subscriber<>(consumer , priority);
		this.subscribers.add(subscriber);
		return subscriber;
	}

	public Subscriber<E> subscribeMethod(Method method , Object instance ,  int priority) throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		return subscribeMethod(lookup , lookup.unreflect(method) , instance , priority);
	}

	@SuppressWarnings("unchecked")
	public Subscriber<E> subscribeMethod(MethodHandles.Lookup lookup , MethodHandle method , Object instance , int priority) throws Throwable {
		if(instance == null)
			return subscribe((Consumer<E>) LambdaMetafactory.metafactory(lookup , "accept" , MethodType.methodType(Consumer.class) ,
				MethodType.methodType(void.class , Object.class) , method , MethodType.methodType(void.class , owner)).getTarget().invokeExact() , priority);
		else
			return subscribe(Consumers.from(((BiConsumer<Object , E>) LambdaMetafactory.metafactory(lookup , "accept" , MethodType.methodType(BiConsumer.class) ,
					MethodType.methodType(void.class , Object.class , Object.class) , method , MethodType.methodType(void.class , instance.getClass() , owner)).getTarget().invoke()), instance) , priority);
	}

	public void unsubscribe(Subscriber<E> subscriber) {
		this.subscribers.remove(subscriber);
	}

	public Collection<Subscriber<E>> getSubscribers() {
		return Collections.unmodifiableCollection(subscribers);
	}

	public int subscribersSize(){
		return subscribers.size();
	}

	private static void registerBus(Class<? extends Event> owner , EventBus<? extends Event> bus){
		if(buses.putIfAbsent(owner , bus) == null)
			new BusRegisterEvent(owner , bus).send();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Event> EventBus<T> getBus(Class<T> eventClass){
		UnsafeAccessor.getUnsafe().ensureClassInitialized(eventClass);
		return (EventBus<T>) buses.get(eventClass);
	}

	public static Map<Class<? extends Event>, EventBus<? extends Event>> getBuses() {
		return Collections.unmodifiableMap(buses);
	}
}