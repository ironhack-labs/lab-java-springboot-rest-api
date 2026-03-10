package org.example.lab_restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "service", "model", "exception", "org.example.lab_restapi"})
public class LabRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabRestApiApplication.class, args);
    }

}
