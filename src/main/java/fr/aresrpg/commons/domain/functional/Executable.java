package fr.aresrpg.commons.domain.functional;

/**
 * A functional executable
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@FunctionalInterface
public interface Executable extends TryExecutable {
	/**
	 * Execute an action
	 */
	void execute();

	/**
	 * Convert this Executable to a java runnable
	 * 
	 * @return a runnable from this executable
	 */
	default Runnable toRunnnable() {
		return this::execute;
	}
}
