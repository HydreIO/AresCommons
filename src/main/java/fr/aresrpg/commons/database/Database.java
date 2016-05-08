package fr.aresrpg.commons.database;

import java.io.Closeable;
import java.util.Iterator;

import fr.aresrpg.commons.Iterators;
import fr.aresrpg.commons.Value;
import fr.aresrpg.commons.database.user.Credential;

@SuppressWarnings("rawtypes")
public interface Database extends Closeable, Value<Collection> {

	/**
	 * Connect to db
	 * 
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * 
	 * @deprecated use {@link Database#connect(Credential)} instead
	 */
	@Deprecated
	void connect(String host, int port, String user, String password);

	void connect(Credential cred);

	void drop(Collection<?> collection);

	<T> Collection<T> create(String id, Class<T> clazz);

	<T> Collection<T>[] getCollections();

	<T> Collection<T> get(String id);

	@Override
	default Iterator<Collection> iterator() {
		return Iterators.of(getCollections());
	}

}
