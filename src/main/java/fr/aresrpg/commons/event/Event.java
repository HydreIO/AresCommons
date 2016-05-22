package fr.aresrpg.commons.event;

public interface Event<T extends Event<T>> {
	EventBus<T> getBus();

	boolean isAsynchronous();

	@SuppressWarnings("unchecked")
	default void send(){
		if(getBus().subscribersSize() != 0)
			if(isAsynchronous())
				EventBus.EXECUTOR.execute(() -> getBus().send((T) this));
			else
				getBus().send((T) this);
	}
}
