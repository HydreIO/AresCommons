package fr.aresrpg.commons.log;

import fr.aresrpg.commons.log.handler.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
	public static final Logger MAIN_LOGGER = new LoggerBuilder("DEFAULT")
			.setUseConsoleHandler(true , true)
			.build();
	public static final String DEFAULT_CHANEL = null;

	public enum Level{
		INFO(false),
		SUCCESS(false),
		WARNING(false),
		DEBUG(false),
		ERROR(true),
		SEVERE(true);

		private final boolean error;

		Level(boolean error){
			this.error = error;
		}

		public boolean isError() {
			return error;
		}
	}

	private final String name;
	private final List<Handler> handlers;

	public Logger(String name, List<Handler> handlers) {
		this.name = name;
		this.handlers = handlers;
	}

	public Logger(String name) {
		this(name , new ArrayList<>());
	}

	public void log(Level level , String channel , Throwable t , String message , Object... args){
		broadcast(level , channel , processArgs(message , args) , t);
	}

	public void log(Level level , Throwable t , String message , Object... args){
		log(level , DEFAULT_CHANEL , t , message , args);
	}

	public void log(Level level , String channel , String message , Object... args){
		log(level , channel , null , message , args);
	}

	public void log(Level level  , String message , Object... args){
		log(level , DEFAULT_CHANEL , message , args);
	}

	public void log(Level level , String channel , Throwable t){
		log(level , channel, t , "");
	}


	public void log(Level level,Throwable t){
		log(level , t);
	}

	//Info
	public void info(String channel , Throwable t , String message , Object... args){
		log(Level.INFO , channel , t,  message , args);
	}

	public void info(Throwable t , String message , Object... args){
		log(Level.INFO , t , message , args);
	}

	public void info(String channel , String message , Object... args){
		log(Level.INFO , channel , message , args);
	}

	public void info(String message , Object... args){
		log(Level.INFO , message , args);
	}

	public void info(String channel , Throwable t){
		log(Level.INFO , channel, t);
	}


	public void info(Throwable t){
		log(Level.INFO , DEFAULT_CHANEL , t);
	}

	//Success
	public void success(String channel , Throwable t , String message , Object... args){
		log(Level.SUCCESS , channel , t,  message , args);
	}

	public void success(Throwable t , String message , Object... args){
		log(Level.SUCCESS , t , message , args);
	}

	public void success(String channel , String message , Object... args){
		log(Level.SUCCESS , channel , message , args);
	}

	public void success(String message , Object... args){
		log(Level.SUCCESS , message , args);
	}

	public void success(String channel , Throwable t){
		log(Level.SUCCESS , channel, t);
	}

	//Warning
	public void warning(String channel , Throwable t , String message , Object... args){
		log(Level.WARNING , channel , t,  message , args);
	}

	public void warning(Throwable t , String message , Object... args){
		log(Level.WARNING, t , message , args);
	}

	public void warning(String channel , String message , Object... args){
		log(Level.WARNING , channel, message , args);
	}

	public void warning(String message , Object... args){
		log(Level.WARNING, message , args);
	}

	public void warning(String channel , Throwable t){
		log(Level.WARNING , channel, t);
	}

	public void warning(Throwable t){
		log(Level.WARNING, t);
	}

	//Debug
	public void debug(String channel , Throwable t , String message , Object... args){
		log(Level.DEBUG , channel , t,  message , args);
	}

	public void debug(Throwable t , String message , Object... args){
		log(Level.DEBUG, t , message , args);
	}

	public void debug(String channel , String message , Object... args){
		log(Level.DEBUG , channel, message , args);
	}

	public void debug(String message , Object... args){
		log(Level.DEBUG , message , args);
	}

	public void debug(String channel , Throwable t){
		log(Level.DEBUG , channel, t);
	}

	public void debug(Throwable t){
		log(Level.DEBUG , t);
	}

	//Error
	public void error(String channel , Throwable t , String message , Object... args){
		log(Level.ERROR , channel , t,  message , args);
	}

	public void error(Throwable t , String message , Object... args){
		log(Level.ERROR, t , message , args);
	}

	public void error(String channel , String message , Object... args){
		log(Level.ERROR , channel, message , args);
	}

	public void error(String message , Object... args){
		log(Level.ERROR , message , args);
	}

	public void error(String channel , Throwable t){
		log(Level.ERROR , channel, t);
	}

	public void error(Throwable t){
		log(Level.ERROR , t);
	}

	//Severe
	public void severe(String channel , Throwable t , String message , Object... args){
		log(Level.ERROR , channel , t,  message , args);
	}

	public void severe(Throwable t , String message , Object... args){
		log(Level.ERROR, t , message , args);
	}

	public void severe(String channel , String message , Object... args){
		log(Level.ERROR , channel, message , args);
	}

	public void severe(String message , Object... args){
		log(Level.ERROR , message , args);
	}

	public void severe(String channel , Throwable t){
		log(Level.ERROR , channel, t);
	}

	public void severe(Throwable t){
		log(Level.ERROR , t);
	}

	private String processArgs(String message , Object...args){
		return message + (args.length == 0 ? "" : Arrays.toString(args));
	}

	public void addHandler(Handler handler){
		handlers.add(handler);
	}

	private void broadcast(Level level, String channel , String message , Throwable t){
		long millis = System.currentTimeMillis();
		try {
			for(Handler handler : handlers)
				handler.handle(level , channel , message , t , millis);
		} catch (IOException e) {
			MAIN_LOGGER.severe(e);
		}
	}

	public String getName() {
		return name;
	}
}