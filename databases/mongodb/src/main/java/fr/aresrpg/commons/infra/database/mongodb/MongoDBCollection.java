package fr.aresrpg.commons.infra.database.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Filter;
import fr.aresrpg.commons.domain.util.Iterators;
import fr.aresrpg.commons.infra.database.mongodb.serialization.DocumentFormat;
import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.serialization.Serializer;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class MongoDBCollection<T> implements Collection<T>{
    private final MongoCollection<Document> collection;
    private Serializer<T , Document , Document> serializer;

    //Hello
    MongoDBCollection(MongoCollection<Document> collection, Serializer<T, Document, Document> serializer) {
        this.collection = collection;
        this.serializer = serializer;
    }

    @Override
    public void put(T t) {
	    try {
		    Document document = new Document();
		    serializer.serialize(document , t , DocumentFormat.INSTANCE);
		    collection.insertOne(document);
	    } catch (IOException e) {
		    Logger.MAIN_LOGGER.severe("MongoDBCollection" , e , "Could'not serialize");
	    }
    }

    @Override
    public void update(Filter filter, T t) {
        try {
            Document document = new Document();
            serializer.serialize(document , t , DocumentFormat.INSTANCE);
            collection.updateOne(toMongoDBFilter(filter) , document);
        } catch (IOException e) {
            Logger.MAIN_LOGGER.severe("MongoDBCollection" , e , "Could'not serialize");
        }
    }

    @Override
    public void putOrUpdate(Filter filter, T t) {
	    if(filter == null)
		    put(t);
	    else
            update(filter , t);
    }

    @Override
    public void updateAll(Filter filter, T t) {
        try {
            Document document = new Document();
            serializer.serialize(document , t , DocumentFormat.INSTANCE);
            collection.updateMany(toMongoDBFilter(filter) , document);
        } catch (IOException e) {
            Logger.MAIN_LOGGER.severe("MongoDBCollection" , e , "Could'not serialize");
        }
    }

    @Override
    public void putOrUpdateAll(Filter filter, T t) {
	    if(filter == null)
		    put(t);
	    else
			updateAll(filter , t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] find(Filter filter, int limit) {
        try {
            T[] found = (T[]) new Object[limit];
            MongoCursor<Document> cursor;
            if(filter == null)
                cursor = collection.find().iterator();
            else
                cursor = collection.find(toMongoDBFilter(filter)).iterator();
            int i;
            for(i = 0 ; cursor.hasNext() && i < limit ; i++)
                    found[i] = serializer.deserialize(cursor.next() , DocumentFormat.INSTANCE);
            return Arrays.copyOf(found , i);
        } catch (IOException e) {
            Logger.MAIN_LOGGER.severe("MongoDBCollection" , e , "Could'not deserialize");
            return (T[]) new Object[0];
        }
    }

    @Override
    public int remove(Filter filter, int limit) {
        MongoCursor<Document> cursor = collection.find(toMongoDBFilter(filter)).iterator();
        int i;
        for(i = 0 ; cursor.hasNext() && i < limit ; i++)
            cursor.remove();
        return i;
    }

    @Override
    public String getId() {
        return null;
    }

    private static Bson toMongoDBFilter(Filter filter){
        if(filter == null)
            return null;
        Bson mfilter = null;
        switch (filter.getType()){
            case AND:
                mfilter = Filters.and(toMongoDBFilters((Filter[]) filter.getValue()));
                break;
            case OR:
                mfilter = Filters.or(toMongoDBFilters((Filter[]) filter.getValue()));
                break;
            case NOR:
                mfilter = Filters.nor(toMongoDBFilters((Filter[]) filter.getValue()));
                break;
            case IN:
                mfilter = Filters.in(filter.getName() , (Object[]) filter.getValue());
                break;
            case NOT_IN:
                mfilter = Filters.nin(filter.getName() , (Object[]) filter.getValue());
                break;
            case EQUALS:
                mfilter = Filters.eq(filter.getName() , filter.getValue());
                break;
            case GREATER:
                mfilter = Filters.gt(filter.getName() , filter.getValue());
                break;
            case GREATER_OR_EQUALS:
                mfilter = Filters.gte(filter.getName() , filter.getValue());
                break;
            case LESS:
                mfilter = Filters.lt(filter.getName() , filter.getValue());
                break;
            case LESS_OR_EQUALS:
                mfilter = Filters.lte(filter.getName() , filter.getValue());
                break;
            case EXISTS:
                mfilter = Filters.exists(filter.getName() , (Boolean) filter.getValue());
                break;
            case ARRAY_SIZE:
                mfilter = Filters.size(filter.getName() , (Integer) filter.getValue());
                break;
            case TEXT:
                mfilter = Filters.text((String) filter.getValue());
                break;
            case REGEX:
                mfilter = Filters.regex(filter.getName() , (String) filter.getValue());
                break;
            default:
                break;
        }
        return mfilter;
    }

    private static Bson[] toMongoDBFilters(Filter... filters){
        Bson[] bfilters = new Bson[filters.length];
        for (int i = 0 ; i < bfilters.length ; i++){
            bfilters[i] = toMongoDBFilter(filters[i]);
        }
        return bfilters;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    @Override
    public boolean isEmpty() {
        return collection.count() == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return Iterators.of(find(null));
    }
}
