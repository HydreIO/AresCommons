package fr.aresrpg.commons.condition.functional.consumer;

@FunctionalInterface
public interface TetraConsumer<A , B , C , D> {
    void accept(A a , B b , C c , D d);

    default TetraConsumer<A , B , C , D> then(TetraConsumer<A , B , C , D> after){
        return (a , b , c ,d) -> {
            accept(a , b , c , d);
            after.accept(a , b , c , d);
        };
    }
}
