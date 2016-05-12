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

	default V safeRemove(K key) {
		return remove(key);
	}

	default boolean safeRemove(K key, V value) {
		return remove(key, value);
	}
}
