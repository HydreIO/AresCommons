package fr.aresrpg.commons.domain.log;

public class AnsiColors {
	public static final char ANSI_CHAR = '\u001B';
	public static final String ANSI_RESET = "\u001B[0m";

	public enum AnsiColor{
		BLACK(30),
		RED(31),
		GREEN(32),
		YELLOW(33),
		BLUE(34),
		PURPLE(35),
		CYAN(36),
		WHITE(37);
		private final int code;

		AnsiColor(int code){
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	private AnsiColors(){}

	public static String getCode(AnsiColor color , AnsiColor background , boolean bright){
		return ANSI_CHAR + "[" + Integer.toString(color.getCode()) +
				(background == null ? "" : ";"+ Integer.toString(10 + background.getCode())) +
				(bright ? ";1" : "") + "m";
	}
}
