package org.example.lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Esto le dice a Spring que busque componentes en los paquetes que creaste fuera de este
@ComponentScan(basePackages = {"controllers", "services", "models", "exceptions", "org.example.lab5"})
public class Lab5Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab5Application.class, args);
    }

}