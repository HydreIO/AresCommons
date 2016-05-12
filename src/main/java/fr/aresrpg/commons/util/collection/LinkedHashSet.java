package fr.aresrpg.commons.util.collection;

import java.util.Collection;

public class LinkedHashSet<E> extends java.util.LinkedHashSet<E> implements Set<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -368478760881676504L;

	public LinkedHashSet(Collection<E> c) {
		super(c);
	}

}
