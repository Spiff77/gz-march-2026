package com.example.demo;

import com.example.demo.configuration.SpringConfiguration;
import com.example.demo.model.Cat;
import com.example.demo.model.Dragon;
import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
// To load a configuration file if you need to create custom beans
//@ImportAutoConfiguration(SpringConfiguration.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
