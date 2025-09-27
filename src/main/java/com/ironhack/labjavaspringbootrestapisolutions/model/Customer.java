package com.ironhack.labjavaspringbootrestapisolutions.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    // Attributes & Validations------------------------------------------------------------------
    //  name (not blank)
    @NotBlank(message = "The name can not be blank")
    private String name;

    //  email (valid email format)
    @Valid()
    private String email;

    //  age (minimum 18)
    @Min(value = 18, message = "The customer has to be at least 18 years old")
    private int age;

    //  address (not blank)
    @NotBlank(message = "The address can not be blank")
    private String address;

    // Constructor------------------------------------------------------------------
    public Customer(){
    }

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getters------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }

    // Setters------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
