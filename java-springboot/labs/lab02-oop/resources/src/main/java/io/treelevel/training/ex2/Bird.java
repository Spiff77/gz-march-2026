package io.treelevel.training.ex2;

/**
 * Exercise 2 — Bird
 *
 * Implements Animal.
 * Extra field: wingspan (double, in cm)
 */
public class Bird implements Animal {

    private final String name;
    private final double wingspan;

    public Bird(String name, double wingspan) {
        this.name     = name;
        this.wingspan = wingspan;
    }

    public double getWingspan() { return wingspan; }

    @Override
    public String getName() { return name; }

    @Override
    public String speak() {
        // TODO: return "Tweet!"
        return "";
    }

    @Override
    public String move() {
        // TODO: return "flies through the air"
        return "";
    }
}
