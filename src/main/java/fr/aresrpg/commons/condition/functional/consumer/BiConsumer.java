package fr.aresrpg.commons.condition.functional.consumer;

@FunctionalInterface
public interface BiConsumer<A , B> {
    void accept(A a , B b);

    default BiConsumer<A , B> then(BiConsumer<A , B> after){
        return (a , b) -> {
            accept(a , b);
            after.accept(a , b);
        };
    }

    static <A , B> BiConsumer<A , B> empty(){
        return (a , b) ->{
            //Empty
        };
    }
}
