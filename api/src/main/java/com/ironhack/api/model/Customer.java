package com.ironhack.api.model;

import jakarta.validation.constraints.*;

public class Customer {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @Min(value = 18, message = "Edad mínima: 18 años")
    private Integer age;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    public Customer() {}

    public Customer(String name, String email, Integer age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
