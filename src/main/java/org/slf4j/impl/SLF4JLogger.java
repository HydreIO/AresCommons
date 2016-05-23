package org.slf4j.impl;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class SLF4JLogger implements Logger{
	private fr.aresrpg.commons.log.Logger instance;

	SLF4JLogger(fr.aresrpg.commons.log.Logger instance) {
		this.instance = instance;
	}

	@Override
	public String getName() {
		return instance.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	@Override
	public void trace(String msg) {
		instance.severe(msg);
	}

	@Override
	public void trace(String format, Object arg) {
		instance.severe(format , arg);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		instance.severe(format , arg1 , arg2);
	}

	@Override
	public void trace(String format, Object... arguments) {
		instance.severe(format , arguments);
	}

	@Override
	public void trace(String msg, Throwable t) {
		instance.severe(t , msg);
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return false;
	}

	@Override
	public void trace(Marker marker, String msg) {

	}

	@Override
	public void trace(Marker marker, String format, Object arg) {

	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {

	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {

	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {

	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public void debug(String msg) {
		instance.debug(msg);
	}

	@Override
	public void debug(String format, Object arg) {
		instance.debug(format , arg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		instance.debug(format , arg1 , arg2);
	}

	@Override
	public void debug(String format, Object... arguments) {
		instance.debug(format , arguments);
	}

	@Override
	public void debug(String msg, Throwable t) {
		instance.debug(t , msg);
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return false;
	}

	@Override
	public void debug(Marker marker, String msg) {

	}

	@Override
	public void debug(Marker marker, String format, Object arg) {

	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {

	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {

	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {

	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public void info(String msg) {
		instance.info(msg);
	}

	@Override
	public void info(String format, Object arg) {
		instance.info(format , arg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		instance.info(format , arg1 , arg2);
	}

	@Override
	public void info(String format, Object... arguments) {
		instance.info(format , arguments);
	}

	@Override
	public void info(String msg, Throwable t) {
		instance.info(t , msg);
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return false;
	}

	@Override
	public void info(Marker marker, String msg) {

	}

	@Override
	public void info(Marker marker, String format, Object arg) {

	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {

	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {

	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {

	}

	@Override
	public boolean isWarnEnabled() {
		return true;
	}

	@Override
	public void warn(String msg) {
		instance.warning(msg);
	}

	@Override
	public void warn(String format, Object arg) {
		instance.warning(format , arg);
	}

	@Override
	public void warn(String format, Object... arguments) {
		instance.warning(format , arguments);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		instance.warning(format , arg1 , arg2);
	}

	@Override
	public void warn(String msg, Throwable t) {
		instance.warning(t , msg);
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return false;
	}

	@Override
	public void warn(Marker marker, String msg) {

	}

	@Override
	public void warn(Marker marker, String format, Object arg) {

	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {

	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {

	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {

	}

	@Override
	public boolean isErrorEnabled() {
		return true;
	}

	@Override
	public void error(String msg) {
		instance.error(msg);
	}

	@Override
	public void error(String format, Object arg) {
		instance.error(format , arg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		instance.error(format , arg1 , arg2);
	}

	@Override
	public void error(String format, Object... arguments) {
		instance.error(format , arguments);
	}

	@Override
	public void error(String msg, Throwable t) {
		instance.error(t , msg);
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return false;
	}

	@Override
	public void error(Marker marker, String msg) {

	}

	@Override
	public void error(Marker marker, String format, Object arg) {

	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {

	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {

	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {

	}

	public fr.aresrpg.commons.log.Logger getInstance() {
		return instance;
	}
}
