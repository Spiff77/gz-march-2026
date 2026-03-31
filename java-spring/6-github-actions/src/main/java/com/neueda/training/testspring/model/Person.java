package com.neueda.training.testspring.model;

public class Person {

    private String name;
    private Pet pet;

    public Person(Pet p, String name) {
        this.pet = p;
        this.name = name;
    }
    

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
