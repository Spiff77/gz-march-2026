package com.example.demo.controller;

import com.example.demo.model.Cat;
import com.example.demo.model.Owner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

    @GetMapping("/showOwner")
    public ResponseEntity<Owner> showOwner() {
        Cat cat = new Cat("Bob", "Yellow", true);
        Owner owner = new Owner("Dan", "Idk", cat);

        return new ResponseEntity<Owner>(owner, HttpStatus.OK);
    }
}
