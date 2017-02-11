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
	 * 
	 * @return the name of this filter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the type of this filter
	 * 
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get the value of this filter
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * The filtered object must match all the filters
	 * 
	 * <pre>
	 * Filter.and(Filter.eq("name", "ororo munroe"), Filter.eq("husband", "blackpanther"));
	 * </pre>
	 * 
	 * is equivalent to
	 * 
	 * <pre>
	 * {@literal return Object if Object.name.equals("ororo munroe") && Object.husband.equals("blackpanther")}
	 * </pre>
	 * 
	 * @param filters
	 *            the filters to combine
	 * @return the created filter
	 */
	public static Filter and(Filter... filters) {
		return new Filter(null, Type.AND, filters);
	}

	/**
	 * The filtered object must match one of the filters
	 * 
	 * <pre>
	 * Filter.any(Filter.eq("name", "ororo munroe"), Filter.eq("husband", "blackpanther"));
	 * </pre>
	 * 
	 * is equivalent to
	 * 
	 * <pre>
	 * return Object if Object.name.equals("ororo munroe") || Object.husband.equals("blackpanther")
	 * </pre>
	 * 
	 * @param filters
	 *            the filters to combine
	 * @return the created filter
	 */
	public static Filter any(Filter... filters) {
		return new Filter(null, Type.OR, filters);
	}

	/**
	 * The filtered object musn't match any of the filters
	 * 
	 * <pre>
	 * Filter.nor(Filter.eq("name", "ororo munroe"), Filter.eq("husband", "blackpanther"));
	 * </pre>
	 * 
	 * is equivalent to
	 * 
	 * <pre>
	 * {@literal return Object if !Object.name.equals("ororo munroe") && !Object.husband.equals("blackpanther")}
	 * </pre>
	 * 
	 * @param filters
	 *            the filters to combine
	 * @return the created filter
	 */
	public static Filter nor(Filter... filters) {
		return new Filter(null, Type.NOR, filters);
	}

	/**
	 * The filtered object need to have the given field set to one of the values
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param values
	 *            the values to match
	 * @return the created filter
	 */
	public static Filter in(String fieldName, Object... values) {
		return new Filter(fieldName, Type.IN, values);
	}

	/**
	 * The filtered object can't have the field set to one of the values
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param values
	 *            the values to match
	 * @return the created filter
	 */
	public static Filter nin(String fieldName, Object... values) {
		return new Filter(fieldName, Type.NOT_IN, values);
	}

	/**
	 * The filtered object must have his field to be equal to the given value
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param value
	 *            the value expected
	 * @return the created filter
	 */
	public static Filter eq(String fieldName, Object value) {
		return new Filter(fieldName, Type.EQUALS, value);
	}

	/**
	 * The filtered object must have his field greater than the given value
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param value
	 *            the value to match
	 * @return the created filter
	 */
	public static Filter gt(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER, value);
	}

	/**
	 * The filtered object must have his field greater than or equal to the given value
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param value
	 *            the value to match
	 * @return the created filter
	 */
	public static Filter gte(String fieldName, Object value) {
		return new Filter(fieldName, Type.GREATER_OR_EQUALS, value);
	}

	/**
	 * The filtered object must have his field lesser than the given value
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param value
	 *            the value to match
	 * @return the created filter
	 */
	public static Filter lt(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS, value);
	}

	/**
	 * The filtered object must have his field greater than or equal to the given value
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param value
	 *            the value to match
	 * @return the created filter
	 */
	public static Filter lte(String fieldName, Object value) {
		return new Filter(fieldName, Type.LESS_OR_EQUALS, value);
	}

	/**
	 * The filtered object need to exist if the @param exists is set to true, and opposite otherwise
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param exists
	 *            if the field exist
	 * @return the created filter
	 */
	public static Filter exists(String fieldName, boolean exists) {
		return new Filter(fieldName, Type.EXISTS, exists);
	}

	/**
	 * The filtered object need to exist
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @return the created filter
	 */
	public static Filter exists(String fieldName) {
		return exists(fieldName, true);
	}

	/**
	 * The filtered array must have the given size
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param size
	 *            the size to match
	 * @return the created filter
	 */
	public static Filter size(String fieldName, int size) {
		return new Filter(fieldName, Type.ARRAY_SIZE, size);
	}

	/**
	 * The filtered object need to have his field set to the given text
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param text
	 *            the text to match
	 * @return the created filter
	 */
	public static Filter text(String fieldName, String text) {
		return new Filter(fieldName, Type.TEXT, text);
	}

	/**
	 * The filtered object need to have his field matching the given regex
	 * 
	 * @param fieldName
	 *            the name of the fields
	 * @param regex
	 *            the text to match
	 * @return the created filter
	 */
	public static Filter regex(String fieldName, String regex) {
		return new Filter(fieldName, Type.REGEX, regex);
	}
}
