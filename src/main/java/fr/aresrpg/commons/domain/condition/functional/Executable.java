package fr.aresrpg.commons.domain.condition.functional;

@FunctionalInterface
public interface Executable {
	void execute();

	default Runnable toRunnnable(){
		return this::execute;
	}
}
