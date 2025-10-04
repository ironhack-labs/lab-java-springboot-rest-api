package com.example.LAB.SpringBoot.REST.API.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Customer {

//    name (not blank)
//    email (valid email format)
//    age (minimum 18)
//    address (not blank)

    @NotEmpty(message = "Name shouldnt be empty")
    private String name;

    @Email(message = "Not valid email format")
    private String email;

    @Min(value = 18, message = "Min value should be 18")
    private int age;

    @NotEmpty(message = "Address shouldnt be blank")
    private String address;

    public String getName()  {
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
