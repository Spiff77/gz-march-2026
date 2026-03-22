package io.treelevel.training.generics;

/**
 * Exercise 2 — Generic Pair
 *
 * A Pair that holds two values of potentially different types.
 *
 * TODO: Add two type parameters <A, B> to this class
 * TODO: Change the fields, constructor, and getters to use A and B
 */
public class Pair {

    private final Object first;
    private final Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
