package io.treelevel.training.generics;

/**
 * Exercise 1 — Generic Box
 *
 * A Box that can hold any type of object.
 *
 * TODO: Add a type parameter <T> to this class
 * TODO: Change the field type, constructor parameter, and getter/setter to use T
 */
public class Box {

    private Object content;

    public Box(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Box{" + content + "}";
    }
}
