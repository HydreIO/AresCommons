package fr.aresrpg.commons.event;

@SuppressWarnings("rawtypes")
public class BusRegisterEvent implements Event<BusRegisterEvent> {
	private static EventBus<BusRegisterEvent> bus = new EventBus<>(BusRegisterEvent.class);

	private final Class<? extends Event> owner;
	private final EventBus registeredBus;

	public BusRegisterEvent(Class<? extends Event> owner, EventBus registeredBus) {
		this.owner = owner;
		this.registeredBus = registeredBus;
	}

	public Class<? extends Event> getBusOwner() {
		return owner;
	}

	public EventBus getRegisteredBus() {
		return registeredBus;
	}

	@Override
	public EventBus<BusRegisterEvent> getBus() {
		return bus;
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
