package com.example.hellolab5;

import jakarta.validation.constraints.*;

public class Customer {
    @NotBlank //not blank = non vuoto
    private String name;

    @Email @NotBlank
    private String email;//(valid email format)

    @Min(18)
    private int age;

    @NotBlank
    private String address;

    // Costruttore senza argomenti
    public Customer() {}

    // Costruttore completo
    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getter e Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}