package org.slf4j.impl;

import fr.aresrpg.commons.log.LoggerBuilder;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory{
	@Override
	public Logger getLogger(String name) {
		return new SLF4JLogger(new LoggerBuilder(name).setUseConsoleHandler(true , false).build());
	}
}
