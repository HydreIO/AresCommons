package fr.aresrpg.commons.domain.database;

public class Filter {
	public enum Type {
		AND(Filter[].class, false),
		OR(Filter[].class, false),
		NOR(Filter[].class, false),
		IN(Object[].class, true),
		NOT_IN(Object[].class, true),
		EQUALS(Object.class, true),
		GREATER(Object.class, true),
		GREATER_OR_EQUALS(Object.class, true),
		LESS(Object.class, true),
		LESS_OR_EQUALS(Object.class, true),
		EXISTS(Boolean.class, true),
		ARRAY_SIZE(Integer.class, true),
		TEXT(String.class, true),
		REGEX(String.class, true);

		private final Class<?> value;
		private final boolean hasName;

		Type(Class<?> value, boolean hasName) {
			this.value = value;
			this.hasName = hasName;
		}

		public Class<?> getValue() {
			return value;
		}

		public boolean hasName() {
			return hasName;
		}
	}

	private String name;
	private Type type;
	private Object value;

	private Filter(String name, Type type, Object value) {
		if (!type.hasName() && name != null) throw new IllegalArgumentException();
		if (!type.getValue().isInstance(value)) throw new IllegalArgumentException();
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}


	public static Filter and(Filter... filters) {
		return new Filter(null, Type.AND, filters);
	}

	public static Filter or(Filter... filters) {
		return new Filter(null, Type.OR, filters);
	}

	public static Filter nor(Filter... filters) {
		return new Filter(null, Type.NOR, filters);
	}

	public static Filter in(String fieldName, Object... values) {
		return new Filter(fieldName, Type.IN, values);
	}

	public static Filter nin(String fieldName, Object... values) {
		return new Filter(fieldName, Type.NOT_IN, values);
	}

	public static Filter eq(String fieldName, Object value) {
		return new Filter(fieldName, Type.EQUALS, value);
	}

	public static Filter gt(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER, value);
	}

	public static Filter gte(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER_OR_EQUALS, value);
	}

	public static Filter lt(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS, value);
	}

	public static Filter lte(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS_OR_EQUALS, value);
	}

	public static Filter exists(String fieldName, boolean exists) {
		return new Filter(fieldName, Type.EXISTS, exists);
	}

	public static Filter exists(String fieldName) {
		return exists(fieldName, true);
	}

	public static Filter size(String fieldName, int size) {
		return new Filter(fieldName, Type.ARRAY_SIZE, size);
	}

	public static Filter text(String fieldName, String text) {
		return new Filter(fieldName, Type.TEXT, text);
	}

	public static Filter regex(String fieldName, String regex) {
		return new Filter(fieldName, Type.REGEX, regex);
	}
}
