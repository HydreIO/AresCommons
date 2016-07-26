package fr.aresrpg.commons.domain.config;

import fr.aresrpg.commons.domain.serialization.formats.Format;

import java.io.InputStream;
import java.io.OutputStream;

public interface Config {
	Format<InputStream, OutputStream> getFormat();
}
