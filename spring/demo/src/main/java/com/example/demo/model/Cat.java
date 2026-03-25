package com.example.demo.model;

public class Cat implements Pet {
    @Override
    public void feed() {
        System.out.println("Cat is enjoying is tiny meal");
    }
}
