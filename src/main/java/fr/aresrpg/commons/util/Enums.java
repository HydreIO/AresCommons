package fr.aresrpg.commons.util;

import java.util.Arrays;

public class Enums {

	private Enums() {

	}

	public static <T extends Enum<T>> T requireType(T t1, T base) {
		if (t1 != base) throw new NotEqualsException("The enum t1 is not equal to base");
		return base;
	}

	@SafeVarargs
	public static <T extends Enum<T>> T requireTypeOr(T t1, T... base) {
		if (Arrays.stream(base).map(t1::equals).reduce((b1, b2) -> b1 || b2).get()) return t1; // NOSONAR auto closeable
		throw new NotEqualsException("The enum t1 is not equal of any from base");
	}

	static class NotEqualsException extends RuntimeException {
		private static final long serialVersionUID = 4844003664180909056L;

		public NotEqualsException() {
			super();
		}

		public NotEqualsException(String s) {
			super(s);
		}

	}

}
