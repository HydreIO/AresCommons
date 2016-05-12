package fr.aresrpg.commons.util.map;

public interface ConcurrentMap<K, V> extends java.util.concurrent.ConcurrentMap<K, V> {

	@Deprecated
	@Override
	public V remove(Object key);

	@Deprecated
	@Override
	public boolean remove(Object key, Object value);

	default V safeRemove(K key) {
		return remove(key);
	}

	default boolean safeRemove(K key, V value) {
		return remove(key, value);
	}

}
