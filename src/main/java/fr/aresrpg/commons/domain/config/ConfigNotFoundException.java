package fr.aresrpg.commons.domain.config;

public class ConfigNotFoundException extends Exception{
	public ConfigNotFoundException(String name , ConfigSource source , Throwable cause) {
		super("The config with name " + name + " was not found on config source " + source , cause);
	}
}
