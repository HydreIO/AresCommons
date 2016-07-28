package fr.aresrpg.commons.domain.functional.consumer;

@FunctionalInterface
public interface TriConsumer<A , B , C> {
    void accept(A a , B b , C c);

    default TriConsumer<A , B , C> then(TriConsumer<A , B , C> after){
        return (a , b , c) -> {
            accept(a , b , c);
            after.accept(a , b , c);
        };
    }
}
