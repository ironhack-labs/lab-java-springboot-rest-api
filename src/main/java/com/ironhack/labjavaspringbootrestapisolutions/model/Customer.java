package com.ironhack.labjavaspringbootrestapisolutions.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @Min(value = 18, message = "You have to be older than 18")
    int age;
    @NotBlank(message = "Adress cannot be empty")
    private String adress;

    public Customer(String name, String email, int age, String adress) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
