package com.example.demo.model;

import org.springframework.stereotype.Component;

public class Dragon implements Pet {
    @Override
    public void feed() {
        System.out.println("Dragon is enjoying is hugggge meal");
    }
}
