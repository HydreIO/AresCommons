package fr.aresrpg.commons.domain.util.map;

public class EnumHashMap<K extends Enum<K>, V> extends java.util.EnumMap<K, V> implements EnumMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6495211753018776776L;

	public EnumHashMap(Class<K> keyType) {
		super(keyType);
	}

}
