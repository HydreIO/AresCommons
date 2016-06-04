package fr.aresrpg.commons.domain.util;

public class Or<F , S> extends Pair<F , S>{

	public Or(F first, S second) {
		super(first, second);
	}

	public boolean isFirst(){
		return first != null;
	}

	public boolean isSecond(){
		return second != null;
	}

	public boolean isTwice(){
		return isFirst() && isSecond();
	}

	public static <T , I> Or<T , I> first(T first){
		return new Or<>(first , null);
	}

	public static <T , I> Or<I , T> second(T second){
		return new Or<>(null , second);
	}

	@Override
	public String toString() {
		return (isFirst() ? first.toString() : "") + (isSecond() ? second.toString() : "");
	}
}
