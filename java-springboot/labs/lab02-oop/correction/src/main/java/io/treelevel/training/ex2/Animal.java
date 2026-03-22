package io.treelevel.training.ex2;

/**
 * Exercise 2 — Animal interface (CORRECTION)
 */
public interface Animal {

    String getName();
    String speak();
    String move();

    default String describe() {
        return String.format("%s the %s says '%s' and %s.",
                getName(), getClass().getSimpleName(), speak(), move());
    }
}
