package com.springboot_lab.model;

import jakarta.validation.constraints.*;

public class Customer {

    @NotBlank(message = "Customer name cannot be blank")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    // Constructors
    public Customer() {
    }

    public Customer(String name, String email, Integer age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getters and Setters
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
