package fr.aresrpg.commons.infra.config;

import fr.aresrpg.commons.domain.config.Config;
import fr.aresrpg.commons.domain.config.ConfigNotFoundException;
import fr.aresrpg.commons.domain.config.ConfigSource;

public class FileConfigSource implements ConfigSource {
	@Override
	public void load(Config config) {

	}

	@Override
	public void save(Config config) throws ConfigNotFoundException {

	}

	@Override
	public void clear(Config config) throws ConfigNotFoundException {

	}
}
