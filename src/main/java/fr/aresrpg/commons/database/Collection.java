package fr.aresrpg.commons.database;

public interface Collection<T> {
	void put(T value);

	void update(Filter filter, T value);

	void putOrUpdate(Filter filter, T value);

	T[] find(Filter filter, int max);

	default T[] find(Filter filter) {
		return find(filter, Integer.MAX_VALUE);
	}

	default T findFirst(Filter filter) {
		return find(filter)[0];
	}

	int remove(Filter filter, int removed);

	default int remove(Filter filter) {
		return remove(filter, 1);
	}

	default boolean exist(Filter filter) {
		return find(filter).length != 0;
	}

}
