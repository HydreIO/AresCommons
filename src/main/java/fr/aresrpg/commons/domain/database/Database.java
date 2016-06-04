package fr.aresrpg.commons.domain.database;

import java.io.Closeable;

import fr.aresrpg.commons.domain.database.user.Credential;

public interface Database extends Closeable {

	/**
	 * Connect to db
	 * 
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 *
	 */
	void connect(String host, int port, String user, String password);

	default void connect(Credential credential) {
		connect(credential.getHostAdress(), credential.getPort(), credential.getUser(), credential.getPass());
	}

	void drop(Collection<?> collection);

	<T> Collection<T> create(String id, Class<T> clazz);

	<T> Collection<T>[] getCollections();

	<T> Collection<T> get(String id, Class<T> clazz);
}
