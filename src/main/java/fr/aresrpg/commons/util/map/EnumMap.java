package fr.aresrpg.commons.util.map;

import java.util.Map;

public interface EnumMap<K extends Enum<K>, V> extends Map<K, V> {

	@Deprecated
	@Override
	public V remove(Object key);

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
