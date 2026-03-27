package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Guangzhou!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloSomeone(@PathVariable String name,
                                  @RequestParam(required = false) String format){
        if(format == null || !format.equals("upper")){
            return "Hello " + name;
        }else{
            return "Hello " + name.toUpperCase();
        }

    }

    @GetMapping("")
    public String sayWelcome(){
        return "Welcome Guangzhou!";
    }
}
