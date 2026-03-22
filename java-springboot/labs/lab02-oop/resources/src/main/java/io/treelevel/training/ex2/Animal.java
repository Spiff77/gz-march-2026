package io.treelevel.training.ex2;

/**
 * Exercise 2 — Animal interface
 *
 * Defines the contract that Dog, Cat, and Bird must fulfill.
 * The default describe() method is already provided — do not modify it.
 */
public interface Animal {

    /** Returns the animal's name. */
    String getName();

    /** Returns the sound the animal makes (e.g., "Woof!"). */
    String speak();

    /** Returns how the animal moves (e.g., "runs on 4 legs"). */
    String move();

    /**
     * Builds a description sentence using the other three methods.
     * Example: "Rex the Dog says 'Woof!' and runs on 4 legs."
     *
     * This default method is fully implemented — you do not need to override it.
     * It uses getClass().getSimpleName() to get the runtime class name.
     */
    default String describe() {
        return String.format("%s the %s says '%s' and %s.",
                getName(), getClass().getSimpleName(), speak(), move());
    }
}
