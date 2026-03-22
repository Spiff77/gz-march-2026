package io.treelevel.training.ex2;

/**
 * Exercise 2 — Dog
 *
 * Implements Animal.
 * Extra field: breed (String)
 */
public class Dog implements Animal {

    private final String name;
    private final String breed;

    public Dog(String name, String breed) {
        this.name  = name;
        this.breed = breed;
    }

    public String getBreed() { return breed; }

    @Override
    public String getName() { return name; }

    @Override
    public String speak() {
        // TODO: return "Woof!"
        return "";
    }

    @Override
    public String move() {
        // TODO: return "runs on 4 legs"
        return "";
    }
}
