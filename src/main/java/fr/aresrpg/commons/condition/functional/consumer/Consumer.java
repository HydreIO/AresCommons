package fr.aresrpg.commons.condition.functional.consumer;

@FunctionalInterface
public interface Consumer<A> {
    void accept(A a );

    default Consumer<A> then(Consumer<A> after){
        return a -> {
            accept(a);
            after.accept(a);
        };
    }

    static <A> Consumer<A> empty(){
        return o ->{
            //Empty
        };
    }
}
