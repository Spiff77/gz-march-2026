package com.example.demo;

import com.example.demo.configuration.SpringConfiguration;
import com.example.demo.model.Cat;
import com.example.demo.model.Dragon;
import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);


        Owner owner = context.getBean(Owner.class);
        owner.getPet().feed();

    }
}
