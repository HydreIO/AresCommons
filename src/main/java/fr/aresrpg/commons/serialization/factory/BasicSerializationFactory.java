package fr.aresrpg.commons.serialization.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fr.aresrpg.commons.reflection.ParametrizedClass;
import fr.aresrpg.commons.serialization.BasicSerializer;
import fr.aresrpg.commons.serialization.Serializer;
import fr.aresrpg.commons.serialization.adapters.Adapter;
import fr.aresrpg.commons.serialization.field.FieldModifier;
import fr.aresrpg.commons.serialization.field.ReflectionFieldModifier;

public class BasicSerializationFactory<I, O> implements SerializationFactory<I, O> {
	private List<Adapter<?, ?>> adapters;
	private FieldModifier fieldModifier;
	private Map<Class<?> , Serializer<? , I , O>> cache;

	public BasicSerializationFactory(List<Adapter<? , ?>> adapters) {
		this.adapters = new ArrayList<>(adapters);
		this.fieldModifier = new ReflectionFieldModifier();
	}

	public BasicSerializationFactory() {
		this.adapters = new ArrayList<>();
		this.fieldModifier = new ReflectionFieldModifier();
	}

	@Override
	public <T> Serializer<T , I , O> createSerializer(Class<T> clazz) {
		Serializer<T , I , O> s = new BasicSerializer<>(this , clazz);
		cache.put(clazz , s);
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Serializer<T , I , O> createOrGetSerializer(Class<T> clazz) {
		Serializer<T , I , O> s = (Serializer<T , I , O>) cache.get(clazz);
		if(s != null)
			return s;
		else
			return createSerializer(clazz);
	}

	@Override
	public List<Adapter<? , ?>> getAdapters() {
		return Collections.unmodifiableList(adapters);
	}


	@Override
	@SuppressWarnings("unchecked")
	public <T> Adapter<T, ?> getAdapter(ParametrizedClass<T> clazz) {
		if (adapters.isEmpty()) return null;
		for (Adapter adapter : adapters)
			if (clazz.equals(adapter.getInType())) return adapter;
		return null;
	}

	@Override
	public void addAdapter(Adapter<? , ?> adapter) {
		adapters.add(adapter);
	}

	@Override
	public void removeAdapter(Adapter<? , ?> adapter) {
		adapters.remove(adapter);
	}

	@Override
	public FieldModifier getFieldModifier() {
		return fieldModifier;
	}

	@Override
	public void setFieldModifier(FieldModifier modifier) {
		this.fieldModifier = modifier;
	}

}
