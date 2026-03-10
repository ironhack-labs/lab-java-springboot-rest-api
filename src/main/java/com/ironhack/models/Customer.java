package com.ironhack.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email fromat")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Adres cannot be blank")
    private String address;

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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
