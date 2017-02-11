package fr.aresrpg.commons.domain.log.handler;

import java.io.FileDescriptor;
import java.io.FileOutputStream;

/**
 * A console handler
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ConsoleHandler extends StreamHandler {
	/**
	 * Create a new Console handler that use stdout and stderr
	 */
	public ConsoleHandler() {
		super(new FileOutputStream(FileDescriptor.out),
				new FileOutputStream(FileDescriptor.err));
	}
}
