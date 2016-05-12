package fr.aresrpg.commons.util.map;

public interface Map<K, V> extends java.util.Map<K, V> {

	@Deprecated
	@Override
	public V remove(Object key);

	@Deprecated
	@Override
	public default boolean remove(Object key, Object value) {
		return java.util.Map.super.remove(key, value);
	}

	@Deprecated
	@Override
	public V get(Object key);

	@Deprecated
	@Override
	public default V getOrDefault(Object key, V defaultValue) {
		return java.util.Map.super.getOrDefault(key, defaultValue);
	}

	default V safeGetOrDefault(K key, V defaultValue) {
		return getOrDefault(key, defaultValue);
	}

	default V safeGet(K key) {
		return get(key);
	}

	default V safeRemove(K key) {
		return remove(key);
	}

	default boolean safeRemove(K key, V value) {
		return remove(key, value);
	}
}
