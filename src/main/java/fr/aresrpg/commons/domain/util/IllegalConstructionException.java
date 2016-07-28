package fr.aresrpg.commons.domain.util;

public class IllegalConstructionException extends IllegalStateException{

	public IllegalConstructionException() {
		super("A construction of this class is not allowed");
	}
}
