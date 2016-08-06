package fr.aresrpg.commons.domain.event;

/**
 * A event fired when a bus is registered in an {@link EventBus}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@SuppressWarnings("rawtypes")
public class BusRegisterEvent implements Event<BusRegisterEvent> {
	public static final EventBus<BusRegisterEvent> BUS = new EventBus<>(BusRegisterEvent.class);

	private final Class<?> owner;
	private final EventBus registeredBus;

	/**
	 * Create a new bus register event
	 * @param owner the owner of the bus
	 * @param registeredBus the instance of the registered bus
	 */
	public BusRegisterEvent(Class<?> owner, EventBus registeredBus) {
		this.owner = owner;
		this.registeredBus = registeredBus;
	}

	/**
	 * Get the registered bus owner
	 * @return the registered bus owner
	 */
	public Class<?> getBusOwner() {
		return owner;
	}

	/**
	 * Get the registered bus
	 * @return the registered bus
	 */
	public EventBus getRegisteredBus() {
		return registeredBus;
	}

	@Override
	public EventBus<BusRegisterEvent> getBus() {
		return BUS;
	}

	@Override
	public boolean isAsynchronous() {
		return true;
	}

	@Override
	public void send() {
		if (getBus() != null) // For self registering
			Event.super.send();
	}

	@Override
	public String toString() {
		return "BusRegisterEvent[" + owner.getName() + "]";
	}
}
