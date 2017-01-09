package fr.aresrpg.commons.infra.database.arangodb;

import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Database;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.util.exception.NotImplementedException;

import java.io.IOException;

import com.arangodb.*;

public class ArangoDBDatabase implements Database {
	private ArangoDriver driver;
	private String database;

	public ArangoDBDatabase(String database) {
		this.database = database;
	}

	public ArangoDBDatabase(ArangoDriver driver) {
		this.driver = driver;
		try {
			this.database = driver.getCurrentDatabase().getName();
		} catch (ArangoException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void connect(String host, int port, String user, String password) {
		ArangoConfigure configure = new ArangoConfigure();
		configure.setArangoHost(new ArangoHost(host, port));
		configure.setUser(user);
		configure.setPassword(password);
		configure.init();

		this.driver = new ArangoDriver(configure, database);
	}

	@Override
	public void drop(Collection<?> collection) {
		throw new NotImplementedException();
	}

	@Override
	public Collection<?>[] getCollections() {
		return (Collection<?>[]) new Object[0];
	}

	@Override
	public <T> Collection<T> get(String id, Class<T> clazz) {
		throw new NotImplementedException();
	}

	@Override
	public <T> Collection<T> get(String id, Class<T> clazz, Serializer<T> serializer) throws IllegalStateException {
		throw new NotImplementedException();
	}

	@Override
	public void close() throws IOException {
		driver = null;
	}

	public String getDatabase() {
		return database;
	}

	public ArangoDriver getDriver() {
		return driver;
	}

}
