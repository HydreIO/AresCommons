package fr.aresrpg.commons.infra.database.arangodb;

import com.arangodb.*;
import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Database;

import java.io.IOException;

public class ArangoDBDatabase implements Database{
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
		configure.setArangoHost(new ArangoHost(host , port));
		configure.setUser(user);
		configure.setPassword(password);
		configure.init();

		this.driver = new ArangoDriver(configure , database);
	}

	@Override
	public void drop(Collection<?> collection) {

	}


	@Override
	public Collection<?>[] getCollections() {
		return (Collection<?>[])new Object[0];
	}

	@Override
	public <T> Collection<T> get(String id, Class<T> clazz) {
		return null;
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
