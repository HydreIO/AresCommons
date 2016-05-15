package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicFormatter implements Formatter{
	private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("HH:mm:ss");

	private DateFormat dateFormat;

	public BasicFormatter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public BasicFormatter() {
		this(DEFAULT_FORMAT);
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String format(Logger.Level level, String channel , String message, String error , long millis) {
		return '[' + dateFormat.format(new Date(millis)) + "]["
				+ Thread.currentThread().getName() + "][" + level +"]"+(channel != null ? '[' + channel + ']' : "")+
				": " + message + '\n' + (error == null ? "" : error);
	}
}
