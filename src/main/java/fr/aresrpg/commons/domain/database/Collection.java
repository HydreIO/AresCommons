package fr.aresrpg.commons.domain.database;

import fr.aresrpg.commons.domain.Value;

/**
 * An database collection
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Collection<T> extends Value<T> {
	/**
	 * Put this value inside of the collection
	 * @param value the value to put
	 */
	void put(T value);

	/**
	 * Update the value in the collection
	 * @param filter the filter to find the value
	 * @param value the value to use for the update
	 */
	void update(Filter filter, T value);

	/**
	 * Put or update the value int he collection
	 * @param filter the filter to find the value
	 * @param value the value to use for the update or put
	 */
	void putOrUpdate(Filter filter, T value);

	/**
	 * Update all the value in the collection
	 * @param filter the filter to find the values
	 * @param value the value to use for the update
	 */
	void updateAll(Filter filter, T value);

	/**
	 * Put or update all the value int he collection
	 * @param filter the filter to find the values
	 * @param value the value to use for the update or put
	 */
	void putOrUpdateAll(Filter filter, T value);

	/**
	 * Find the values in th collection
	 * @param filter the filter to use
	 * @param max the maximum of value to get
	 * @return the values found
	 */
	T[] find(Filter filter, int max);

	/**
	 * Find the values in th collection
	 * @param filter the filter to use
	 * @return the values found
	 */
	default T[] find(Filter filter) {
		return find(filter, Integer.MAX_VALUE);
	}

	default T findFirst(Filter filter) {
		T[] found = find(filter , 1);
		if(found.length >= 1)
			return found[0];
		else
			return null;
	}

	/**
	 * Remove the values matching the filter
	 * @param filter the filter to use
	 * @param removed the maximum of values to remove
	 * @return the number of values removed
	 */
	int remove(Filter filter, int removed);

	/**
	 * Remove a value from the collection
	 * @param filter the filter to found the value
	 * @return true if the value has bean removed
	 */
	default boolean remove(Filter filter) {
		return remove(filter, 1) == 1;
	}

	/**
	 * Get if the values exist in the database
	 * @param filter the filter to use
	 * @return if the value exist
	 */
	default boolean exist(Filter filter) {
		return find(filter).length != 0;
	}

	/**
	 * Get the name of this collection
	 * @return the id of this collection
	 */
	String getId();

}
