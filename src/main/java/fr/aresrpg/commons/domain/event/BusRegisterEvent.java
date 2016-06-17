package fr.aresrpg.commons.domain.event;

@SuppressWarnings("rawtypes")
public class BusRegisterEvent implements Event<BusRegisterEvent> {
	public static final EventBus<BusRegisterEvent> BUS = new EventBus<>(BusRegisterEvent.class);

	private final Class<?> owner;
	private final EventBus registeredBus;

	public BusRegisterEvent(Class<?> owner, EventBus registeredBus) {
		this.owner = owner;
		this.registeredBus = registeredBus;
	}

	public Class<?> getBusOwner() {
		return owner;
	}

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
