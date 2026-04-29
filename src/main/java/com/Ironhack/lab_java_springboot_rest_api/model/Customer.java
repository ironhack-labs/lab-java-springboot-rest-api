package com.Ironhack.lab_java_springboot_rest_api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "Formato de email inválido")
    private String email;

    @Min(value = 18, message = "El cliente debe ser mayor de 18 años")
    private int age;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    public Customer() {
    }

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }
}
