package fr.aresrpg.commons.infra.database.mongodb;

import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Database;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.infra.serialization.unsafe.UnsafeSerializationFactory;

import java.io.IOException;
import java.util.*;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

public class MongoDBDatabase implements Database {
	private MongoDatabase database;
	private MongoClient client;
	private String name;
	private Map<String, MongoDBCollection> collections;
	private SerializationFactory factory;

	public MongoDBDatabase(MongoDatabase database, MongoClient client) {
		this.database = database;
		this.client = client;
		this.name = database.getName();
		this.collections = new HashMap<>();
		this.factory = new UnsafeSerializationFactory();
	}

	public MongoDBDatabase(String name) {
		this.name = name;
		this.collections = new HashMap<>();
		this.factory = new UnsafeSerializationFactory();
	}

	/**
	 * @return the factory
	 */
	public SerializationFactory getFactory() {
		return factory;
	}

	/**
	 * @return the database
	 */
	public MongoDatabase getRawDatabase() {
		return database;
	}

	/**
	 * @return the client
	 */
	public MongoClient getClient() {
		return client;
	}

	@Override
	public void connect(String host, int port, String user, String password) throws IOException {
		client = new MongoClient(new ServerAddress(host, port), Collections.singletonList(MongoCredential.createCredential(user, name, password.toCharArray())));
		database = client.getDatabase(name);
	}

	@Override
	public void close() {
		client.close();
	}

	@Override
	public void drop(Collection<?> collection) {
		((MongoDBCollection) collection).getCollection().drop();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<?>[] getCollections() {
		return collections.values().toArray((Collection<?>[]) new Object[collections.size()]);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Collection<T> get(String id, Class<T> type) throws IllegalStateException {
		return get(id, type, factory.createSerializer(type));
	}

	@Override
	public <T> Collection<T> get(String id, Class<T> type, Serializer<T> serializer) throws IllegalStateException {
		if (database == null) throw new IllegalStateException("Unable to get the collection ! The database is not connected.");
		MongoDBCollection<T> collection = collections.get(id);
		if (collection != null) return collection;
		collection = new MongoDBCollection<>(database.getCollection(id), serializer, type);
		collections.put(id, collection);
		return collection;
	}
}
