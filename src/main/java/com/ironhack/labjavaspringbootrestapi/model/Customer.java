package com.ironhack.labjavaspringbootrestapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @NotBlank(message = "Customer name cannot be blank")
    private String name;

    @Email(message = "Customer email must be valid")
    @NotBlank(message = " Email cannot be blank")
    private String email;

    @Min(value = 18, message = "Customer must be at least 18 years old")
    private int age;

    @NotBlank(message = "Customer address cannot be blank")
    private String address;

    //constructor vacio
    public Customer() {
    }

    //constructor con todos los parametros
    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    //Getters y setters

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
