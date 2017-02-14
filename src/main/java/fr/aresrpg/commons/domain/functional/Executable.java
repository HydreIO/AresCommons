package fr.aresrpg.commons.domain.functional;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A functional executable
 * 
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 */
@FunctionalInterface
public interface Executable extends TryExecutable {
	/**
	 * Execute an action
	 */
	void execute();

	/**
	 * Convert this Executable to a java runnable
	 * 
	 * @return a runnable from this executable
	 */
	default Runnable toRunnnable() {
		return this::execute;
	}

	default void whileValid(Predicate<Void> pred) {
		whileValid(pred::test);
	}

	default void whileValid(Supplier<Boolean> supp) {
		while (supp.get())
			execute();
	}

	default void whileValidAsync(Predicate<Void> pred) {
		whileValidAsync(() -> pred.test(null));
	}

	default void whileValidAsync(Supplier<Boolean> supp) {
		CompletableFuture.runAsync(() -> {
			while (supp.get())
				execute();
		});
	}
}
