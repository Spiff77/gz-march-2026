package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Owner {

    private Pet pet;

    // Instead of @Autowired, I can use the constructor
    public Owner( Pet pet) {
        this.pet = pet;
    }

    public void sayHello(){
        System.out.println("Hello, I am a owner");
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
