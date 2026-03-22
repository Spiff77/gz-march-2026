package io.treelevel.training.ex2;

/**
 * Exercise 2 — Cat
 *
 * Implements Animal.
 * Extra field: indoor (boolean) — getter is named isIndoor()
 */
public class Cat implements Animal {

    private final String  name;
    private final boolean indoor;

    public Cat(String name, boolean indoor) {
        this.name   = name;
        this.indoor = indoor;
    }

    /** Returns true if the cat lives indoors. */
    public boolean isIndoor() { return indoor; }

    @Override
    public String getName() { return name; }

    @Override
    public String speak() {
        // TODO: return "Meow!"
        return "";
    }

    @Override
    public String move() {
        // TODO: return "prowls silently"
        return "";
    }
}
