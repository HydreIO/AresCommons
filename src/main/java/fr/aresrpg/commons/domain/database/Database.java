package fr.aresrpg.commons.domain.database;

import java.io.Closeable;

public interface Database extends Closeable {

	/**
	 * Connect to database
	 * @param host host to connect
	 * @param port the port to use
	 * @param user the user to use
	 * @param password the password to use
	 */
	void connect(String host, int port, String user, String password);

	/**
	 * Delete the provided collection
	 * @param collection the collection to delete
	 */
	void drop(Collection<?> collection);

	/**
	 * Create a collection
	 * @param id the name of the new collection
	 * @param clazz the type of the documents in collection
	 * @param <T> the type of the collection
	 * @return the created collection
	 */
	<T> Collection<T> create(String id, Class<T> clazz);

	/**
	 * Get the collections in the database
	 * @return the collections in the database
	 */
	Collection<?>[] getCollections();

	/**
	 * Get the a collection using the provided id
	 * @param id the name of the collection
	 * @param clazz the type of the documents in collection
	 * @param <T> the type of the collection
	 * @return the collection
	 */
	<T> Collection<T> get(String id, Class<T> clazz);
}
