package fr.aresrpg.commons.domain.concurrent;

public interface ConcurrentMap<K, V> extends java.util.concurrent.ConcurrentMap<K, V> {

	@Deprecated
	@Override
	public V remove(Object key);

	@Deprecated
	@Override
	public boolean remove(Object key, Object value);

	@Deprecated
	@Override
	public V get(Object key);

	@Deprecated
	@Override
	public default V getOrDefault(Object key, V defaultValue) {
		return java.util.concurrent.ConcurrentMap.super.getOrDefault(key, defaultValue);
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
