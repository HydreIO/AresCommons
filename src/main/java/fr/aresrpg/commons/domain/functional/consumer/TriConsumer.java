package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer with 3 arguments
 * @param <A> the first argument type
 * @param <B> the second argument type
 * @param <C> the third argument type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface TriConsumer<A , B , C> {
    /**
     * Execute an action
     * @param a the first argument
     * @param b the second argument
     * @param c the third argument
     */
    void accept(A a , B b , C c);

    /**
     * Create an new consumer that execute this consumer an after an other
     * @param after the consumer to execute after
     * @return a new consumer
     */
    default TriConsumer<A , B , C> then(TriConsumer<A , B , C> after){
        return (a , b , c) -> {
            accept(a , b , c);
            after.accept(a , b , c);
        };
    }
}
