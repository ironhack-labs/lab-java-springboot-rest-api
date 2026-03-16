package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a Customer entity with validation constraints.
 * This class models a customer with name, email, age, and address.
 */
public class Customer {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Address is required")
    private String address;

    /**
     * Constructor to initialize a Customer object.
     *
     * @param name    The name of the customer.
     * @param email   The email of the customer.
     * @param age     The age of the customer.
     * @param address The address of the customer.
     */
    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getters

    /**
     * Gets the name of the customer.
     *
     * @return The customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the customer.
     *
     * @return The customer email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the age of the customer.
     *
     * @return The customer age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The customer address.
     */
    public String getAddress() {
        return address;
    }

    // Setters

    /**
     * Sets the name of the customer.
     *
     * @param name The new name for the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email The new email for the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age The new age for the customer.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The new address for the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}