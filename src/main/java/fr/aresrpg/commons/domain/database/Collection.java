package fr.aresrpg.commons.domain.database;

import fr.aresrpg.commons.domain.Value;

/**
 * Represent a database collection
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public interface Collection<T> extends Value<T> {
	/**
	 * Put the value in the collection
	 * 
	 * @param value
	 *            the value
	 */
	void put(T value);

	/**
	 * Update the value in the collection
	 * 
	 * @param filter
	 *            the filter to find the value
	 * @param value
	 *            the new value
	 */
	void update(Filter filter, T value);

	/**
	 * Put or update the value in the collection
	 * 
	 * @param filter
	 *            the filter to find the value
	 * @param value
	 *            the new value
	 */
	void putOrUpdate(Filter filter, T value);

	/**
	 * Update all the value in the collection
	 * 
	 * @param filter
	 *            the filter to find the values
	 * @param value
	 *            the new value
	 */
	void updateAll(Filter filter, T value);

	/**
	 * Put or update all the value in the collection
	 * 
	 * @param filter
	 *            the filter to find the values
	 * @param value
	 *            the new value
	 */
	void putOrUpdateAll(Filter filter, T value);

	/**
	 * Find the values in the collection
	 * 
	 * @param filter
	 *            the filter to use
	 * @param max
	 *            the maximum of value to get
	 * @return the values found or null if there was no values
	 */
	T[] find(Filter filter, int max);

	/**
	 * Find the values in the collection
	 * 
	 * @param filter
	 *            the filter to use
	 * @return the values found or null if there was no values
	 */
	default T[] find(Filter filter) {
		return find(filter, Integer.MAX_VALUE);
	}

	/**
	 * Find the first value corresponding to the filter
	 * 
	 * @param filter
	 *            the filter
	 * @return the value or null if there is no value corresponding to the filter
	 */
	default T findFirst(Filter filter) {
		T[] found = find(filter, 1);
		if (found.length >= 1) return found[0];
		else return null;
	}

	/**
	 * Remove the values matching the filter
	 * 
	 * @param filter
	 *            the filter to use
	 * @param removed
	 *            the maximum of values to remove
	 * @return the number of values removed
	 */
	int remove(Filter filter, int removed);

	/**
	 * Remove a value from the collection
	 * 
	 * @param filter
	 *            the filter to find the value
	 * @return true if the value has bean removed
	 */
	default boolean remove(Filter filter) {
		return remove(filter, 1) == 1;
	}

	/**
	 * Get if the values exist in the database
	 * 
	 * @param filter
	 *            the filter to use
	 * @return true if the value exist
	 */
	default boolean exist(Filter filter) {
		return find(filter).length != 0;
	}

	/**
	 * @return the number of document in the collection
	 */
	long count();

	/**
	 * Get the name of this collection
	 * 
	 * @return the id of this collection
	 */
	String getId();

}
