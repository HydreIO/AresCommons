package fr.aresrpg.commons.database;

import fr.aresrpg.commons.Value;

import java.io.Closeable;

public interface Database<T> extends Closeable , Value<Collection<T>> {

    void connect(String host , int port , String user , String password);

    void drop(Collection<T> collection);

    Collection<T> create(String id);

    Collection<T>[] getCollections();

    Collection<T> get(String id);

}
