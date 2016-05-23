package fr.aresrpg.commons.log.handler.formatters;

import fr.aresrpg.commons.log.AnsiColors;
import fr.aresrpg.commons.log.Log;
import fr.aresrpg.commons.log.Logger;

public class ColorFormatter implements Formatter{
	private Formatter parent;

	public ColorFormatter(Formatter parent) {
		this.parent = parent;
	}

	public String getColor(Logger.Level level){
		String color;
		switch (level){
			case INFO:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.YELLOW , null , false);
				break;
			case DEBUG:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.PURPLE , null , false);
				break;
			case ERROR:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.RED , null , false);
				break;
			case SEVERE:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.RED , null , true);
				break;
			case SUCCESS:
				color = AnsiColors.getCode(AnsiColors.AnsiColor.GREEN , null , false);
				break;
			default:
				color = "";
				break;
		}
		return color;
	}

	@Override
	public String format(Log log, ErrorFormatter errorFormatter) {
		return getColor(log.getLevel()) + parent.format(log , errorFormatter) + AnsiColors.ANSI_RESET;
	}
}
