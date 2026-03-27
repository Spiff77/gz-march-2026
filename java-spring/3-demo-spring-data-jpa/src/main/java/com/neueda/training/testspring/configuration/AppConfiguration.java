package com.neueda.training.testspring.configuration;

import com.neueda.training.testspring.model.Dog;
import com.neueda.training.testspring.model.Person;
import com.neueda.training.testspring.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Pet pet() {
        return new Dog();
    }

    @Bean
    public Person person(@Autowired Pet pet) {
        return new Person(pet, "Sharique");
    }
}
