package fr.aresrpg.commons.domain.config;

/**
 * <p>
 * An interface to provide config who works with a bundle object.
 * <br>
 * The bundle object is a DAO who contains public fields like this :
 * </p>
 * 
 * <pre>
 * public final class MyBundle {
 * 
 * 	private MyBundle() {}
 * 	
 *	&#64;Configured("animals.fish.")
 * 	public String fooFish = "bar";
 *	
 *	&#64;Configured("animals.cat.")
 * 	public int kitty = 80
 * 
 * }
 * 
 * </pre>
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
public interface Config {

	/**
	 * <p>
	 * Load the configuration and overwrite the bundle object
	 * </p>
	 * 
	 * @return the config for chainage purpose
	 * @throws ConfigNotFoundException if the config is not found on this source
	 */
	default Config load(ConfigSource source) throws ConfigNotFoundException {
		source.load(this);
		return this;
	}

	/**
	 * Write the bundle object wich contains all the configurations field to the config file on the disk
	 * 
	 * @return the config for chainage purpose
	 * @throws ConfigNotFoundException
	 *             if the config is not found on source
	 *
	 */
	default Config save(ConfigSource source) throws ConfigNotFoundException{
		source.save(this);
		return this;
	}

	/**
	 * <p>
	 * Delete all lines on the config file ! This method can be used to delete all trace of critical information on the disk (for exemple when a bad guy invite himself on your instance)
	 * </p>
	 * <p>
	 * The user must call a clear after a load, again for security purpose ! there is no need to keep hard informations when they are in runtime<br>
	 * Remember that a config must be used to provide informations to the application not to store them at runtime, use a database instead
	 * </p>
	 * <p>
	 * <b>
	 * If you don't have a script or a deployment manager like ansible who can provide a config file before running your app then think again
	 * </b>
	 * </p>
	 * 
	 * @return the config for chainage purpose
	 * @throws ConfigNotFoundException
	 *             if the config is not found on source
	 */
	default Config clear() throws ConfigNotFoundException{
		return this;
	}

	/**
	 * Get this config name that must be used when it's stored
	 *
	 * <p>
	 * This name must be unique to avoid replacement in config source ({@link ConfigSource}
	 * @return the unique name of this config
	 */
	String getName();

}
