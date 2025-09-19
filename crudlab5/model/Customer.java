package com.crud.crudlab5.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email should be valid.")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Adress cannot be blank")
    private String address;

    public Customer(){

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

