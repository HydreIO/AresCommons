package fr.aresrpg.commons.log.handler.raven;

import com.getsentry.raven.Raven;
import com.getsentry.raven.RavenFactory;
import com.getsentry.raven.dsn.Dsn;
import com.getsentry.raven.environment.RavenEnvironment;
import com.getsentry.raven.event.Event;
import com.getsentry.raven.event.EventBuilder;
import com.getsentry.raven.event.interfaces.*;
import com.getsentry.raven.util.Util;
import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.Logger;
import fr.aresrpg.commons.log.handler.Handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class RavenHandler implements Handler {
	public static final String THREAD_NAME = "Thread name";
	public static final String THREAD_ID = "Thread id";
	public static final String ARGUMENTS = "Arguments";

	protected final Raven raven;
	protected final String release;
	protected final String serverName;


	public RavenHandler(Raven raven, String release, String serverName) {
		this.raven = raven;
		this.release = release;
		this.serverName = serverName;
	}

	public RavenHandler(Raven raven) {
		this(raven , null , null);
	}

	public RavenHandler(String dsn) {
		this(RavenFactory.ravenInstance(dsn), null , null);
	}

	public RavenHandler(Dsn dsn) {
		this(RavenFactory.ravenInstance(dsn), null , null);
	}

	public RavenHandler(Dsn dsn ,  String release, String serverName) {
		this(RavenFactory.ravenInstance(dsn), release , serverName);
	}

	@Override
	public void handle(Log log) throws IOException {
		RavenEnvironment.startManagingThread();
		try{
			raven.sendEvent(createEvent(log));
		}finally {
			RavenEnvironment.stopManagingThread();
		}
	}

	protected Event createEvent(Log log){
		EventBuilder eventBuilder = new EventBuilder()
				.withMessage(log.getMessage())
				.withTag(ARGUMENTS , Arrays.toString(log.getArgs()))
				.withTimestamp(new Date(log.getMillis()))
				.withLogger(log.getLogger().getName())
				.withLevel(convertLevel(log.getLevel()))
				.withFingerprint(log.getBaseMessage());

		if(!Util.isNullOrEmpty(serverName))
			eventBuilder.withServerName(serverName.trim());

		if(!Util.isNullOrEmpty(release))
			eventBuilder.withRelease(release.trim());

		if(log.getThrowable() != null)
			eventBuilder.withSentryInterface(new ExceptionInterface(log.getThrowable()));
		else if(log.getSource() != null)
			eventBuilder.withSentryInterface(new StackTraceInterface(new StackTraceElement[]{log.getSource()}));
		if(log.getSource() != null)
			eventBuilder.withCulprit(log.getSource());

		eventBuilder.withExtra(THREAD_NAME , log.getThread().getName());
		eventBuilder.withExtra(THREAD_ID , log.getThread().getId());

		raven.runBuilderHelpers(eventBuilder);

		return eventBuilder.build();
	}

	protected Event.Level convertLevel(Logger.Level baseLevel){
		Event.Level level = null;
		switch (baseLevel){
			case INFO:
				level = Event.Level.INFO;
				break;
			case DEBUG:
			case SUCCESS:
				level = Event.Level.DEBUG;
				break;
			case WARNING:
				level = Event.Level.WARNING;
				break;
			case ERROR:
				level = Event.Level.ERROR;
				break;
			case SEVERE:
				level = Event.Level.FATAL;
				break;
			default:
				break;
		}
		return level;
	}
}
