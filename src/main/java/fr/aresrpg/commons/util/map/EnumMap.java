package fr.aresrpg.commons.util.map;

import java.util.Map;

public interface EnumMap<K extends Enum<K>, V> extends Map<K, V> {

	@Deprecated
	@Override
	public V remove(Object key);

	@Deprecated
	@Override
	public V get(Object key);

	@Deprecated
	@Override
	public default V getOrDefault(Object key, V defaultValue) {
		return Map.super.getOrDefault(key, defaultValue);
	}

	default V safeGet(K key) {
		return get(key);
	}

	default V safeGetOrDefault(K key, V value) {
		return getOrDefault(key, value);
	}

	@Deprecated
	@Override
	public default boolean remove(Object key, Object value) {
		return Map.super.remove(key, value);
	}

	default V safeRemove(Enum<K> k) {
		return remove(k);
	}

	default boolean safeRemove(Enum<K> k, V v) {
		return remove(k, v);
	}

}
