package com.ironhack.restapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    private int id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email should be a valid format")
    private String email;
    @Min(value = 18, message = "Age must be at least 18")
    private int age;
    @NotBlank(message = "Address cannot be blank")
    private String address;

    public Customer(int id, String name, String email, int age, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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