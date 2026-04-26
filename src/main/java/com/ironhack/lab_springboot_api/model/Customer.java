package com.ironhack.lab_springboot_api.model;

import jakarta.validation.constraints.*;

public class Customer {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "Formato de email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Min(value = 18, message = "Debe ser mayor de 18 años")
    private int age;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre es obligatorio") String name) {
        this.name = name;
    }

    public @Email(message = "Formato de email inválido") @NotBlank(message = "El email es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Formato de email inválido") @NotBlank(message = "El email es obligatorio") String email) {
        this.email = email;
    }

    public @Min(value = 18, message = "Debe ser mayor de 18 años") int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Debe ser mayor de 18 años") int age) {
        this.age = age;
    }

    public @NotBlank(message = "La dirección es obligatoria") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "La dirección es obligatoria") String address) {
        this.address = address;
    }
}