package fr.aresrpg.commons.util.function;

import java.util.Collection;
import java.util.Map;

/**
 * Remove if contains without throwing
 * 
 * @author MrSceat
 *
 */
public interface Remover {
	public static <K, V> void remove(K key, Map<K, V> map) {
		if (map.containsKey(key)) map.remove(key);
	}

	public static <V> void remove(V value, Collection<V> coll) {
		if (coll.contains(value)) coll.remove(value);
	}
}
