package com.neueda.training.testspring.controller;

import com.neueda.training.testspring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    Person person;

    // Constructor injection.
    public HelloController(Person person) {
        this.person = person;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello/param")
    public String sayHelloWithParam(@RequestParam String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloWithPathVariable(@PathVariable String name) {
        return "Hello " + name + " (from path variable)!";
    }

    @GetMapping("/showPerson")
    public Person showPerson() {
        return this.person;
    }
}
