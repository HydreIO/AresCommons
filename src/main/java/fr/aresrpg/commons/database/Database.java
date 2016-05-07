package fr.aresrpg.commons.database;

import fr.aresrpg.commons.Iterators;
import fr.aresrpg.commons.Value;

import java.io.Closeable;
import java.util.Iterator;

public interface Database extends Closeable , Value<Collection> {

    void connect(String host , int port , String user , String password);

    void drop(Collection<?> collection);

    <T> Collection<T> create(String id , Class<T> clazz);

    Collection[] getCollections();

    <T> Collection<T> get(String id);

    @Override
    default Iterator<Collection> iterator(){
        return Iterators.of(getCollections());
    }

}
