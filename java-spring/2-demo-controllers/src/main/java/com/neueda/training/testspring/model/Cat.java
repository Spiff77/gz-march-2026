package com.neueda.training.testspring.model;

public class Cat implements Pet {

    public String communication = "Meaw";

    public void feed() {
        System.out.println("Feed cat");
    }
}
