package io.treelevel.training.ex2;

/**
 * Exercise 2 — Cat (CORRECTION)
 */
public class Cat implements Animal {

    private final String  name;
    private final boolean indoor;

    public Cat(String name, boolean indoor) {
        this.name   = name;
        this.indoor = indoor;
    }

    public boolean isIndoor() { return indoor; }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Meow!"; }
    @Override public String move()     { return "prowls silently"; }

    @Override
    public String toString() {
        return String.format("Cat{name='%s', indoor=%b}", name, indoor);
    }
}
