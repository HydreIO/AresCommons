package fr.aresrpg.commons.infra.database.mongodb;

import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Filter;
import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.infra.database.mongodb.serialization.DocumentFormat;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDBCollection<T> implements Collection<T> {
	public static final UpdateOptions UPSERT = new UpdateOptions().upsert(true);
	public static final String FIELD_MONGO = "MongoDBCollection";
	private final MongoCollection<Document> collection;
	private Serializer<T, Document, Document> serializer;
	private Class<T> clazz;

	MongoDBCollection(MongoCollection<Document> collection, Serializer<T, Document, Document> serializer, Class<T> clazz) {
		this.collection = collection;
		this.serializer = serializer;
		this.clazz = clazz;
	}

	/**
	 * @return the clazz
	 */
	public Class<T> getClazz() {
		return clazz;
	}

	@Override
	public void put(T t) {
		try {
			Document document = new Document();
			serializer.serialize(document, t, DocumentFormat.INSTANCE);
			collection.insertOne(new Document("$set", document));
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not serialize");
		}
	}

	@Override
	public void update(Filter filter, T t) {
		try {
			Document document = new Document();
			serializer.serialize(document, t, DocumentFormat.INSTANCE);
			collection.updateOne(toMongoDBFilter(filter), new Document("$set", document));
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not serialize");
		}
	}

	@Override
	public void putOrUpdate(Filter filter, T t) {
		try {
			Document document = new Document();
			serializer.serialize(document, t, DocumentFormat.INSTANCE);
			collection.updateOne(toMongoDBFilter(filter), new Document("$set", document), UPSERT);
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not serialize");
		}
	}

	@Override
	public void updateAll(Filter filter, T t) {
		try {
			Document document = new Document();
			serializer.serialize(document, t, DocumentFormat.INSTANCE);
			collection.updateMany(toMongoDBFilter(filter), new Document("$set", document));
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not serialize");
		}
	}

	@Override
	public void putOrUpdateAll(Filter filter, T t) {
		try {
			Document document = new Document();
			serializer.serialize(document, t, DocumentFormat.INSTANCE);
			collection.updateMany(toMongoDBFilter(filter), new Document("$set", document), UPSERT);
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not serialize");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] find(Filter filter, int limit) {
		if (limit == 0) return (T[]) new Object[0];

		try {
			T[] found = (T[]) Array.newInstance(getClazz(), limit);
			MongoCursor<Document> cursor;
			if (filter == null)
				cursor = collection.find().iterator();
			else
				cursor = collection.find(toMongoDBFilter(filter)).iterator();
			int i;
			for (i = 0; cursor.hasNext() && i < limit; i++)
				found[i] = serializer.deserialize(cursor.next(), DocumentFormat.INSTANCE);
			return Arrays.copyOf(found, i);
		} catch (IOException e) {
			Logger.MAIN_LOGGER.severe(FIELD_MONGO, e, "Could'not deserialize");
			return (T[]) new Object[0];
		}
	}

	@Override
	public int remove(Filter filter, int limit) {
		MongoCursor<Document> cursor = collection.find(toMongoDBFilter(filter)).iterator();
		int i;
		for (i = 0; cursor.hasNext() && i < limit; i++)
			cursor.remove();
		return i;
	}

	@Override
	public long count() {
		return collection.count();
	}

	@Override
	public String getId() {
		return collection.getNamespace().getCollectionName();
	}

	private static Bson toMongoDBFilter(Filter filter) {
		if (filter == null)
			return null;
		Bson mfilter = null;
		switch (filter.getType()) {
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
				mfilter = Filters.in(filter.getName(), (Object[]) filter.getValue());
				break;
			case NOT_IN:
				mfilter = Filters.nin(filter.getName(), (Object[]) filter.getValue());
				break;
			case EQUALS:
				mfilter = Filters.eq(filter.getName(), filter.getValue());
				break;
			case GREATER:
				mfilter = Filters.gt(filter.getName(), filter.getValue());
				break;
			case GREATER_OR_EQUALS:
				mfilter = Filters.gte(filter.getName(), filter.getValue());
				break;
			case LESS:
				mfilter = Filters.lt(filter.getName(), filter.getValue());
				break;
			case LESS_OR_EQUALS:
				mfilter = Filters.lte(filter.getName(), filter.getValue());
				break;
			case EXISTS:
				mfilter = Filters.exists(filter.getName(), (Boolean) filter.getValue());
				break;
			case ARRAY_SIZE:
				mfilter = Filters.size(filter.getName(), (Integer) filter.getValue());
				break;
			case TEXT:
				mfilter = Filters.text((String) filter.getValue());
				break;
			case REGEX:
				mfilter = Filters.regex(filter.getName(), (String) filter.getValue());
				break;
			default:
				break;
		}
		return mfilter;
	}

	private static Bson[] toMongoDBFilters(Filter... filters) {
		Bson[] bfilters = new Bson[filters.length];
		for (int i = 0; i < bfilters.length; i++) {
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
		collection.find().map(v -> {
			try {
				return serializer.deserialize(v, DocumentFormat.INSTANCE);
			} catch (Exception e) {
				Logger.MAIN_LOGGER.error(e);
			}
			return null;
		}).iterator();
		return null;
	}
}