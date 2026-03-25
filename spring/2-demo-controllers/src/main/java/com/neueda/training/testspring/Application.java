package com.neueda.training.testspring;

import com.neueda.training.testspring.configuration.AppConfiguration;
import com.neueda.training.testspring.model.Dog;
import com.neueda.training.testspring.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
@ImportAutoConfiguration(AppConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
