package com.ironhack.springbootrestapi.model;

import com.ironhack.springbootrestapi.exception.InvalidCustomerInput;

public class Customer {
    private String name;
    private String email;
    private int age;
    private String address;

    public Customer(String name, String email, int age, String address) {
        setName(name);
        setEmail(email);
        setAge(age);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new InvalidCustomerInput("Name must be at least 1 character long");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 18) {
            throw new InvalidCustomerInput("Age must be at least 18 years old");
        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            throw new InvalidCustomerInput("Invalid email address");
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address == null || address.trim().isEmpty()) {
            throw new InvalidCustomerInput("Address must be at least 1 character long");
        }
        this.address = address;
    }
}
