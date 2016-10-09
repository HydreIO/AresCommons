package fr.aresrpg.commons.infra.database.arangodb;

import fr.aresrpg.commons.domain.database.Collection;
import fr.aresrpg.commons.domain.database.Filter;

import java.util.Iterator;

import com.arangodb.ArangoDriver;
import com.arangodb.ArangoException;
import com.arangodb.entity.CollectionEntity;

public class ArangoDBCollection<T> implements Collection<T> {

	private final CollectionEntity collection;
	private final ArangoDriver driver;
	private final Class<T> clazz;

	public ArangoDBCollection(CollectionEntity collection, ArangoDriver driver, Class<T> clazz) {
		this.collection = collection;
		this.driver = driver;
		this.clazz = clazz;
	}

	@Override
	public void put(T value) {
		try {
			driver.createDocument(collection.getName(), value);
		} catch (ArangoException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void update(Filter filter, T value) {
		
	}

	@Override
	public void putOrUpdate(Filter filter, T value) {

	}

	@Override
	public void updateAll(Filter filter, T value) {

	}

	@Override
	public void putOrUpdateAll(Filter filter, T value) {

	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] find(Filter filter, int max) {
		return (T[]) new Object[0];
	}

	@Override
	public int remove(Filter filter, int removed) {
		return 0;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public String getId() {
		return collection.getName();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		try {
			Iterator<String> documentHandler = driver.getDocuments(collection.getName()).iterator();
			return new Iterator<T>() {
				@Override
				public boolean hasNext() {
					return documentHandler.hasNext();
				}

				@Override
				public T next() {
					try {
						return driver.getDocument(documentHandler.next(), clazz).getEntity();
					} catch (ArangoException e) {
						throw new IllegalStateException(e);
					}
				}
			};
		} catch (ArangoException e) {
			throw new IllegalStateException(e);
		}
	}

	public ArangoDriver getDriver() {
		return driver;
	}

	public CollectionEntity getCollection() {
		return collection;
	}
}
