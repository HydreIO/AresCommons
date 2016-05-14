package fr.aresrpg.commons.database;

public interface Collection<T> {
	void put(T value);

	void update(Filter filter, T value);

	void putOrUpdate(Filter filter, T value);

	void updateAll(Filter filter, T value);

	void putOrUpdateAll(Filter filter, T value);

	T[] find(Filter filter, int max);

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

	int remove(Filter filter, int removed);

	default int remove(Filter filter) {
		return remove(filter, 1);
	}

	default boolean exist(Filter filter) {
		return find(filter).length != 0;
	}

}
