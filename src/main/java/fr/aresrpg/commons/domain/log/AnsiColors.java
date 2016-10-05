package fr.aresrpg.commons.domain.log;

/**
 * An class to generate ansi color character combination
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class AnsiColors {
	/**
	 * The ansi color char
	 */
	public static final char ANSI_CHAR = '\u001B';
	/**
	 * The ansi color reset char
	 */
	public static final String ANSI_RESET = ANSI_CHAR + "[0m";

	/**
	 * An ansi available colors enum
	 * 
	 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
	 */
	public enum AnsiColor {
		BLACK(30),
		RED(31),
		GREEN(32),
		YELLOW(33),
		BLUE(34),
		PURPLE(35),
		CYAN(36),
		WHITE(37);
		private final int code;

		AnsiColor(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	private AnsiColors() {
	}

	/**
	 * Get the character sequence to set the color
	 * 
	 * @param color
	 *            the color of the text
	 * @param background
	 *            the background color
	 * @param bright
	 *            if the color is bright
	 * @return a character sequence aka {@link String}
	 */
	public static String getCode(AnsiColor color, AnsiColor background, boolean bright) {
		return ANSI_CHAR + "[" + Integer.toString(color.getCode()) + (background == null ? "" : ";" + Integer.toString(10 + background.getCode())) + (bright ? ";1" : "") + "m";
	}
}
