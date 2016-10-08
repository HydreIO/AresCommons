package fr.aresrpg.commons.infra.config;

import fr.aresrpg.commons.domain.config.Config;
import fr.aresrpg.commons.domain.config.ConfigNotFoundException;
import fr.aresrpg.commons.domain.config.ConfigSource;
import fr.aresrpg.commons.domain.config.ConfiguredFieldNamer;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.infra.serialization.formats.JsonFormat;

import java.io.*;

public class FileConfigSource implements ConfigSource {
	private final SerializationFactory factory;
	private final File folder;
	private final String defaultType;

	public FileConfigSource(SerializationFactory factory, File folder, String defaultType) {
		this.factory = factory;
		this.folder = folder;
		this.defaultType = defaultType;
		factory.setFieldNamer(new ConfiguredFieldNamer());
	}

	public FileConfigSource(SerializationFactory factory, File folder) {
		this(factory , folder , "json");
	}

	@Override
	public void load(Config config) throws ConfigNotFoundException{
		File f = searchFile(config.getName());
		if(f == null)
			f = new File(folder , config.getName() + '.' + defaultType);
		if(!f.exists())
			save(config);
		try {
			factory.createOrGetSerializer((Class<Config>)config.getClass())
					.deserialize(new FileInputStream(f) , config, JsonFormat.INSTANCE); //TODO: Change this
		} catch (IOException e) {
			throw new ConfigNotFoundException(config.getName() , this , e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void save(Config config) throws ConfigNotFoundException {
		File f = searchFile(config.getName());
		if(f == null)
			f = new File(folder , config.getName() + '.' + defaultType);
		try {
			if(!f.exists())
				f.createNewFile(); // NOSONAR Catch later
			factory.createOrGetSerializer((Class<Config>)config.getClass())
					.serialize(new FileOutputStream(f) , config , JsonFormat.INSTANCE); //TODO: Change this
		} catch (IOException e) {
			throw new ConfigNotFoundException(config.getName() , this , e);
		}
	}

	@Override
	public void clear(Config config) throws ConfigNotFoundException {
		File f = searchFile(config.getName());
		if(f == null)
			throw new ConfigNotFoundException(config.getName() , this , null);
		else
			if(!f.delete())
				f.deleteOnExit();
	}

	private File searchFile(String name) {
		for(File file : folder.listFiles()){
			String s = file.getName();
			int dot = s.lastIndexOf('.');
			if(dot != -1)
				s = s.substring(0 , dot);
			if(!file.isDirectory() && s.substring(0 , dot).equals(name))
				return file;
		}
		return null;
	}
}
