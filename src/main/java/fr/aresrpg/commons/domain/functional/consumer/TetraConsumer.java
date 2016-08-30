package fr.aresrpg.commons.domain.functional.consumer;

/**
 * A consumer with 4 arguments
 * @param <A> the first argument type
 * @param <B> the second argument type
 * @param <C> the third argument type
 * @param <D> the fourth argument type
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
@FunctionalInterface
public interface TetraConsumer<A , B , C , D> {
	/**
     * Execute an action
     * @param a the first argument
     * @param b the second argument
     * @param c the third argument
     * @param d the fourth argument
     */
    void accept(A a , B b , C c , D d);

    /**
     * Create an new consumer that execute this consumer an after an other
     * @param after the consumer to execute after
     * @return a new consumer
     */
    default TetraConsumer<A , B , C , D> then(TetraConsumer<A , B , C , D> after){
        return (a , b , c ,d) -> {
            accept(a , b , c , d);
            after.accept(a , b , c , d);
        };
    }
}
