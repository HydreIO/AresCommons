package fr.aresrpg.commons.domain.concurrent;

import java.util.concurrent.CopyOnWriteArraySet;

import fr.aresrpg.commons.domain.util.collection.Set;

public class ConcurrentSet<E> extends CopyOnWriteArraySet<E> implements Set<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6095184195904687940L;

}
