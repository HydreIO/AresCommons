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

	/**
	 * Get the name of this filter
	 * @return the name of this filter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the type of this filter
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get the value of this filter
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	/**
	 * Create a filter that combine all the filters
	 * @param filters the filters to combine
	 * @return the created filter
	 */
	public static Filter and(Filter... filters) {
		return new Filter(null, Type.AND, filters);
	}

	/**
	 * Create a filter that match one or all the filters
	 * @param filters the filters to combine
	 * @return the created filter
	 */
	public static Filter or(Filter... filters) {
		return new Filter(null, Type.OR, filters);
	}

	/**
	 * Create a filter that match one of the filters
	 * @param filters the filters to combine
	 * @return the created filter
	 */
	public static Filter nor(Filter... filters) {
		return new Filter(null, Type.NOR, filters);
	}

	/**
	 * Create a filter that match if the field has one of the values
	 * @param fieldName the name of the fields
	 * @param values the values to match
	 * @return the created filter
	 */
	public static Filter in(String fieldName, Object... values) {
		return new Filter(fieldName, Type.IN, values);
	}

	/**
	 * Create a filter that match if the field don't have one of the values
	 * @param fieldName the name of the fields
	 * @param values the values to match
	 * @return the created filter
	 */
	public static Filter nin(String fieldName, Object... values) {
		return new Filter(fieldName, Type.NOT_IN, values);
	}

	/**
	 * Create a filter that match if the field is equal to one of the values
	 * @param fieldName the name of the fields
	 * @return the created filter
	 */
	public static Filter eq(String fieldName, Object value) {
		return new Filter(fieldName, Type.EQUALS, value);
	}

	/**
	 * Create a filter that match if the field is greater than the value
	 * @param fieldName the name of the fields
	 * @param value the value to match
	 * @return the created filter
	 */
	public static Filter gt(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER, value);
	}

	/**
	 * Create a filter that match if the field is greater or equal than the value
	 * @param fieldName the name of the fields
	 * @param value the value to match
	 * @return the created filter
	 */
	public static Filter gte(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER_OR_EQUALS, value);
	}

	/**
	 * Create a filter that match if the field is less than than the value
	 * @param fieldName the name of the fields
	 * @param value the value to match
	 * @return the created filter
	 */
	public static Filter lt(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS, value);
	}

	/**
	 * Create a filter that match if the field is less than or equal than the value
	 * @param fieldName the name of the fields
	 * @param value the value to match
	 * @return the created filter
	 */
	public static Filter lte(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS_OR_EQUALS, value);
	}

	/**
	 * Create a filter that match if the field exist
	 * @param fieldName the name of the fields
	 * @param exists if the field exist
	 * @return the created filter
	 */
	public static Filter exists(String fieldName, boolean exists) {
		return new Filter(fieldName, Type.EXISTS, exists);
	}

	/**
	 * Create a filter that match if the field exist
	 * @param fieldName the name of the fields
	 * @return the created filter
	 */
	public static Filter exists(String fieldName) {
		return exists(fieldName, true);
	}

	/**
	 * Create a filter that match if the field has the given size
	 * @param fieldName the name of the fields
	 * @param size the size to match
	 * @return the created filter
	 */
	public static Filter size(String fieldName, int size) {
		return new Filter(fieldName, Type.ARRAY_SIZE, size);
	}

	/**
	 * Create a filter that match if the field has the text
	 * @param fieldName the name of the fields
	 * @param text the text to match
	 * @return the created filter
	 */
	public static Filter text(String fieldName, String text) {
		return new Filter(fieldName, Type.TEXT, text);
	}

	/**
	 * Create a filter that match if the field match the regex
	 * @param fieldName the name of the fields
	 * @param regex the text to match
	 * @return the created filter
	 */
	public static Filter regex(String fieldName, String regex) {
		return new Filter(fieldName, Type.REGEX, regex);
	}
}
