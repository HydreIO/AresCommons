package fr.aresrpg.commons.condition.functional.function;


@FunctionalInterface
public interface Function<A , R> {
    R apply(A a);

    default <V> Function<V , R> applyBefore(Function<V , A> before){
        return v -> apply(before.apply(v));
    }

    default <V> Function<A , V> applyAfter(Function<R , V> after){
        return a -> after.apply(apply(a));
    }

    static <T> Function<T , T> identity(){
        return t -> t;
    }
}
