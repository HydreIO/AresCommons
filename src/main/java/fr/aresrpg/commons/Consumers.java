package fr.aresrpg.commons;

import fr.aresrpg.commons.condition.functional.consumer.PentaConsumer;
import fr.aresrpg.commons.condition.functional.consumer.TetraConsumer;
import fr.aresrpg.commons.condition.functional.consumer.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Consumers {
    private Consumers(){}

    public static <A, B> Consumer<B> from(BiConsumer<A, B> f, A a) {
        return b -> f.accept(a, b);
    }

    public static <A, B, C> Consumer<C> from(TriConsumer<A, B, C> f, A a, B b) {
        return c -> f.accept(a, b, c);
    }

    public static <A, B, C, D> Consumer<D> from(TetraConsumer<A, B, C, D> f, A a, B b, C c) {
        return d -> f.accept(a, b, c, d);
    }

    public static <A, B, C, D, E> Consumer<E> from(PentaConsumer<A, B, C, D, E> f, A a, B b, C c, D d) {
        return e -> f.accept(a, b, c, d, e);
    }
}
