package fr.aresrpg.commons.log;

public class Logger implements Log {

	private static Logger logger = new Logger();

	@Override
	public void logOut(String log) {
		// TODO Auto-generated method stub
	}

	@Override
	public void logTrace(Exception e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void logTrace(Throwable t) {
		// TODO Auto-generated method stub
	}

	public static Logger getLogger() {
		return logger;
	}

}