package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer with 5 arguments
 * @param <A> the first argument type
 * @param <B> the second argument type
 * @param <C> the third argument type
 * @param <D> the fourth argument type
 * @param <E> the fifth argument type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface PentaConsumer<A , B , C , D , E> {
    /**
     * Execute an action
     * @param a the first argument
     * @param b the second argument
     * @param c the third argument
     * @param d the fourth argument
     * @param e the fifth argument
     */
    void accept(A a , B b , C c , D d , E e);

    /**
     * Create an new consumer that execute this consumer an after an other
     * @param after the consumer to execute after
     * @return a new consumer
     */
    default PentaConsumer<A , B , C , D , E> then(PentaConsumer<A , B , C , D , E> after){
        return (a , b , c , d , e) -> {
            accept(a , b , c , d , e);
            after.accept(a , b , c , d , e);
        };
    }
}
