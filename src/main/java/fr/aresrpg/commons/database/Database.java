package fr.aresrpg.commons.database;

import java.util.Iterator;

import fr.aresrpg.commons.Iterators;
import fr.aresrpg.commons.Value;
import fr.aresrpg.commons.database.user.Credential;

@SuppressWarnings("rawtypes")
public interface Database extends Value<Collection> {

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

	void close();

	void drop(Collection<?> collection);

	<T> Collection<T> create(String id, Class<T> clazz);

	<T> Collection<T>[] getCollections();

	<T> Collection<T> get(String id);

	@Override
	default Iterator<Collection> iterator() {
		return Iterators.of(getCollections());
	}

}
