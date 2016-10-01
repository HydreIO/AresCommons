package fr.aresrpg.commons.domain.serialization.factory;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.adapters.Adapter;

import java.util.*;

public abstract class AbstractSerializationFactory implements SerializationFactory {
	private List<Adapter<?, ?>> adapters;
	private Map<Class<?>, Serializer<?>> cache;

	public AbstractSerializationFactory(List<Adapter<?, ?>> adapters) {
		this.adapters = new ArrayList<>(adapters);
		this.cache = new HashMap<>();
	}

	public AbstractSerializationFactory() {
		this.adapters = new ArrayList<>();
		this.cache = new HashMap<>();
	}

	@Override
	public <T> Serializer<T> createSerializer(Class<T> clazz) {
		Serializer<T> s = createSerializerInstance(clazz);
		cache.put(clazz, s);
		return s;
	}

	protected abstract <T> Serializer<T> createSerializerInstance(Class<T> clazz);

	@Override
	@SuppressWarnings("unchecked")
	public <T> Serializer<T> createOrGetSerializer(Class<T> clazz) {
		Serializer<T> s = (Serializer<T>) cache.get(clazz);
		if (s != null) return s;
		else return createSerializer(clazz);
	}

	@Override
	public List<Adapter<?, ?>> getAdapters() {
		return Collections.unmodifiableList(adapters);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Adapter<T, ?> getAdapter(ParametrizedClass<T> clazz) {
		if (adapters.isEmpty()) return null;
		for (@SuppressWarnings("rawtypes")
		Adapter adapter : adapters)
			if (clazz.equals(adapter.getInType())) return adapter;
		return null;
	}

	@Override
	public void addAdapter(Adapter<?, ?> adapter) {
			if(adapters.add(adapter))
				cache.clear();
	}

	@Override
	public void removeAdapter(Adapter<?, ?> adapter) {
		if(adapters.remove(adapter))
			cache.clear();
	}
}
