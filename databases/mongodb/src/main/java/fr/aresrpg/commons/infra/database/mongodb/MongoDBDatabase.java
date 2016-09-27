package fr.aresrpg.commons.infra.database.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Database;
import fr.aresrpg.commons.infra.serialization.factory.BasicSerializationFactory;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import org.bson.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MongoDBDatabase implements Database{
    private MongoDatabase database;
    private MongoClient client;
    private String name;
    private Map<String , MongoDBCollection> collections;
    private SerializationFactory<Document , Document> factory;

    public MongoDBDatabase(MongoDatabase database , MongoClient client) {
        this.database = database;
        this.client = client;
        this.name = database.getName();
        this.collections = new HashMap<>();
        this.factory = new BasicSerializationFactory<>();
    }

    public MongoDBDatabase(String name) {
        this.name = name;
        this.collections = new HashMap<>();
        this.factory = new BasicSerializationFactory<>();
    }

    @Override
    public void connect(String host, int port, String user, String password) {
        client = new MongoClient(new ServerAddress(host , port), Collections.singletonList(MongoCredential.createCredential(user , name , password.toCharArray())));
        database = client.getDatabase(name);
    }

    @Override
    public void close() {
       client.close();
    }

    @Override
    public void drop(Collection<?> collection) {
        ((MongoDBCollection)collection).getCollection().drop();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<?>[] getCollections() {
        return collections.values().toArray((Collection<?>[]) new Object[collections.size()]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Collection<T> get(String id , Class<T> type) {
        if(database == null)
            throw new IllegalStateException("Not connected to database use connect()");
        MongoDBCollection<T> collection = collections.get(id);
	    if(collection != null)
		    return collection;
        collection = new MongoDBCollection<>(database.getCollection(id) , factory.createSerializer(type));
	    collections.put(id , collection);
	    return collection;
    }
}
