package com.example.demo.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank(message = "name cannot be blank")
    private String name;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(String name, String address, int age, String email) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    @Email(message = "Inappropriate email text")
    private String email;
    @Min(value = 18,message = "Age cannot be less than 18")
    private int age;
    @NotBlank(message = "Address cannot be blank")
    private String address;
}
