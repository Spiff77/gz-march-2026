package io.treelevel.training.ex2;

/**
 * Exercise 2 — Bird (CORRECTION)
 */
public class Bird implements Animal {

    private final String name;
    private final double wingspan;

    public Bird(String name, double wingspan) {
        this.name     = name;
        this.wingspan = wingspan;
    }

    public double getWingspan() { return wingspan; }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Tweet!"; }
    @Override public String move()     { return "flies through the air"; }

    @Override
    public String toString() {
        return String.format("Bird{name='%s', wingspan=%.1f cm}", name, wingspan);
    }
}
