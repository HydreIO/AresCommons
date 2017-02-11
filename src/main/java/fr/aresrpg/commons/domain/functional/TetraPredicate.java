package fr.aresrpg.commons.domain.functional;

/**
 * A tetra predicate class
 * 
 * @since 1.7
 */
@FunctionalInterface
public interface TetraPredicate<FI, SE, TH, FO> {

	/**
	 * Test 4 values
	 * 
	 * @param fi
	 *            the first value
	 * @param se
	 *            the second value
	 * @param th
	 *            the third value
	 * @param fo
	 *            the fourth velue
	 * @return true if the predicate is valid, false otherwise
	 */
	boolean test(FI fi, SE se, TH th, FO fo);

	default void ifValid(FI fi, SE se, TH th, FO fo, Runnable action) {
		if (test(fi, se, th, fo)) action.run();
	}

}
