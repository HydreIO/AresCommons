package fr.aresrpg.commons.domain.condition.functional;

@FunctionalInterface
public interface Executable extends TryExecutable{
	void execute();

	default Runnable toRunnnable(){
		return this::execute;
	}
}
