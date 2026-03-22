package io.treelevel.training.ex2;

/**
 * Exercise 2 — Dog (CORRECTION)
 */
public class Dog implements Animal {

    private final String name;
    private final String breed;

    public Dog(String name, String breed) {
        this.name  = name;
        this.breed = breed;
    }

    public String getBreed() { return breed; }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Woof!"; }
    @Override public String move()     { return "runs on 4 legs"; }

    @Override
    public String toString() {
        return String.format("Dog{name='%s', breed='%s'}", name, breed);
    }
}
