package fr.aresrpg.commons.domain.plugin;

public interface Plugin<T> {

	void enable(T value);

	void disable();

}
