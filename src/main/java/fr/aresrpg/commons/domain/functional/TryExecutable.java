package fr.aresrpg.commons.domain.functional;

/**
 * A functional executable that can throw exception
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface TryExecutable {
	/**
     * Execute an action
     * @throws Exception if an error occurred
     */
    void execute() throws Exception;
}
