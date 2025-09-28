package com.ironhack.labweek5.model;

//name (not blank)
//email (valid email format)
//age (minimum 18)
//address (not blank)

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer{
    @NotBlank(message = "Cannot be empty")
    private String name;

    @Email(message = "Must be a valid email address")
    private String email;

    @Min(value = 18, message = "Must be over 18")
    private int age;

    @NotBlank(message = "Cannot be empty")
    private String address;

    //getters and setters

    public String getName(){
        return name;
    }

    public void setName (String name){
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

    //constructor
    public Customer(String name, String email, int age, String address){
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

}