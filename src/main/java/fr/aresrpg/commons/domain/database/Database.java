package fr.aresrpg.commons.domain.database;

import java.io.Closeable;
import java.io.IOException;

public interface Database extends Closeable {

	/**
	 * Connect to database
	 * 
	 * @param host
	 *            host to connect
	 * @param port
	 *            the port to use
	 * @param user
	 *            the user to use
	 * @param password
	 *            the password to use
	 * @throws IOException
	 *             if the database isn't reachable for whatever reason
	 */
	void connect(String host, int port, String user, String password) throws IOException;

	/**
	 * Delete the provided collection
	 * 
	 * @param collection
	 *            the collection to delete
	 */
	void drop(Collection<?> collection);

	/**
	 * Get the collections in the database
	 * 
	 * @return the collections in the database
	 */
	Collection<?>[] getCollections();

	/**
	 * <p>
	 * Get the a collection using the provided id and create it if there is no collection for the id
	 * </p>
	 * 
	 * @param id
	 *            the name of the collection
	 * @param clazz
	 *            the type of the documents in collection
	 * @param <T>
	 *            the type of the collection
	 * @return the collection
	 */
	<T> Collection<T> get(String id, Class<T> clazz);
}
