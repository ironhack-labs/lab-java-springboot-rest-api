package com.ironhack.labjavaspringbootrestapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18, message = "Customer must be at least 18")
    private Integer age;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    public Customer() {
    }

    public Customer(String name, String email, Integer age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
