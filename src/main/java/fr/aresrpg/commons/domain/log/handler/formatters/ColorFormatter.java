package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.*;
import fr.aresrpg.commons.domain.log.AnsiColors.AnsiColor;

/**
 * A Proxy class for {@link Formatter} that add ansi colors
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ColorFormatter implements Formatter {
	private Formatter parent;

	/**
	 * Create a new color formatter that add color to his parent
	 * 
	 * @param parent
	 *            the formatter to add color
	 */
	public ColorFormatter(Formatter parent) {
		this.parent = parent;
	}

	/**
	 * Get the ansi color for this {@link fr.aresrpg.commons.domain.log.Logger.Level}
	 * 
	 * @param level
	 *            the level to use
	 * @return the color
	 */
	public String getColor(Logger.Level level) {
		switch (level) {
			case INFO:
				return AnsiColors.getCode(AnsiColor.BLUE, null, false);
			case WARNING:
				return AnsiColors.getCode(AnsiColor.YELLOW, null, false);
			case DEBUG:
				return AnsiColors.getCode(AnsiColor.PURPLE, null, false);
			case ERROR:
				return AnsiColors.getCode(AnsiColor.RED, null, false);
			case SEVERE:
				return AnsiColors.getCode(AnsiColor.RED, null, true);
			case SUCCESS:
				return AnsiColors.getCode(AnsiColor.GREEN, null, false);
			default:
				return "";
		}
	}

	@Override
	public String format(Log log, ErrorFormatter errorFormatter) {
		return getColor(log.getLevel()) + parent.format(log, errorFormatter) + AnsiColors.ANSI_RESET;
	}
}
