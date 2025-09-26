package com.antoninrgb.labjavarestapilocal.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @NotBlank(message= "Name cannot be blank")
    String name;

    @Email(message= "Invalid email format")
    @NotBlank(message= "Email cannot be blank")
    String email;

    @Min(value = 18, message= "Must be older than 18")
    int age;

    @NotBlank(message= "Address can't be blank")
    String address;

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

    public String getInfo() {
        return "Name: " + name + ", email: " + email + ", age: " + age + ", address: " + address;
    }

}
