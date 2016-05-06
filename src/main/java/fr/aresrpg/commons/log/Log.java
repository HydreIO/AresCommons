package fr.aresrpg.commons.log;

public interface Log {

	void logOut(String log);

	void logTrace(Exception e);

	void logTrace(Throwable t);

	public static void out(String log) {
		Logger.getLogger().logOut(log);
	}

	public static void trace(Exception e) {
		Logger.getLogger().logTrace(e);
	}

	public static void trace(Throwable t) {
		Logger.getLogger().logTrace(t);
	}
}