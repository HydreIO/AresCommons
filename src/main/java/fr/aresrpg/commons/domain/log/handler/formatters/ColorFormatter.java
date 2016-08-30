package fr.aresrpg.commons.domain.log.handler.formatters;

import fr.aresrpg.commons.domain.log.Logger;
import fr.aresrpg.commons.domain.log.AnsiColors;
import fr.aresrpg.commons.domain.log.Log;

/**
 * A Proxy class for {@link Formatter} that add ansi colors
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public class ColorFormatter implements Formatter {
	private Formatter parent;

	/**
	 * Create a new color formatter thaht add color to his parent
	 * @param parent the formatter to add color
	 */
	public ColorFormatter(Formatter parent) {
		this.parent = parent;
	}

	/**
	 * Get the ansi color for this {@link fr.aresrpg.commons.domain.log.Logger.Level}
	 * @param level the level to use
	 * @return the color
	 */
	public String getColor(Logger.Level level) {
		String color;
		switch (level) {
			case INFO:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.YELLOW, null, false);
				break;
			case DEBUG:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.PURPLE, null, false);
				break;
			case ERROR:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.RED, null, false);
				break;
			case SEVERE:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.RED, null, true);
				break;
			case SUCCESS:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.GREEN, null, false);
				break;
			default:
				color = "";
				break;
		}
		return color;
	}

	@Override
	public String format(Log log, ErrorFormatter errorFormatter) {
		return getColor(log.getLevel()) + parent.format(log, errorFormatter) + AnsiColors.ANSI_RESET;
	}
}
