package fr.aresrpg.commons.domain.util;

/**
 * A runtime exception to avoid illegal construction of util classes
 * @author Duarte David <deltaduartedavid @ gmail.com>
 */
public class IllegalConstructionException extends IllegalStateException{

	public IllegalConstructionException() {
		super("A construction of this class is not allowed");
	}
}
