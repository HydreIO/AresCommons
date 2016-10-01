package fr.aresrpg.commons.domain.config;

interface ConfigSource {
	void load(Config config);

	void save(Config config);
}
