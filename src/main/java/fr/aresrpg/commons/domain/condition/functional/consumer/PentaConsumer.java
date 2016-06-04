package fr.aresrpg.commons.domain.condition.functional.consumer;

@FunctionalInterface
public interface PentaConsumer<A , B , C , D , E> {
    void accept(A a , B b , C c , D d , E e);

    default PentaConsumer<A , B , C , D , E> then(PentaConsumer<A , B , C , D , E> after){
        return (a , b , c , d , e) -> {
            accept(a , b , c , d , e);
            after.accept(a , b , c , d , e);
        };
    }
}
