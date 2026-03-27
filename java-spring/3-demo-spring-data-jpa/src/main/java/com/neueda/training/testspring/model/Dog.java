package com.neueda.training.testspring.model;

public class Dog implements Pet {

    public String communication = "Woof";

    public void feed() {
        System.out.println("feed dog");
    }
}
