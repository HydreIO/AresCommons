package fr.aresrpg.commons.domain.plugin;

/**
 * Represent a plugin
 * @param <T> the type of the plugin holder
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public interface Plugin<T> {
	/**
	 * Call to Enable this plugin
	 * @param value the instance of the plugin holder
	 */
	void enable(T value);

	/**
	 * Called before the disablement of a plugin
	 */
	void disable();

}
