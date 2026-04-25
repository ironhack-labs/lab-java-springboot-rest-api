package com.ironhacklab5.productapiapplication.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a customer.
 * Validated on incoming requests — all fields are required and have format rules.
 */
public class Customer {

    @NotBlank(message = "Customer name must not be blank")
    private String name;

    // Must follow a valid email format, e.g. user@example.com
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    // Minimum age requirement is 18
    @Min(value = 18, message = "Customer must be at least 18 years old")
    private int age;

    @NotBlank(message = "Address must not be blank")
    private String address;

    // --- Constructors ---

    public Customer() {}

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // --- Getters and Setters ---

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
