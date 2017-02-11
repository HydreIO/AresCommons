package fr.aresrpg.commons.domain.config;

public interface ConfigSource {
	void load(Config config) throws ConfigNotFoundException;

	void save(Config config) throws ConfigNotFoundException;

	void clear(Config config) throws ConfigNotFoundException;
}
