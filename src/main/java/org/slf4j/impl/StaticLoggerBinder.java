package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder{

	/**
	 * The unique instance of this class.
	 */
	private static StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	private LoggerFactory factory = new LoggerFactory();
	private StaticLoggerBinder(){}

	public static StaticLoggerBinder getSingleton() {
		return SINGLETON;
	}

	@Override
	public ILoggerFactory getLoggerFactory() {
		return factory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return factory.getClass().getName();
	}
}
