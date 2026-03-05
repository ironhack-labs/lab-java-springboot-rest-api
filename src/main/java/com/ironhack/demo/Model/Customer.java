package com.ironhack.demo.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Customer {
@NotBlank
private String name;

@NotBlank(message = "email can not be empty")
@Email( message = "Email must be valid")
private String email ;

@Min(value = 18, message = "Age must be at least 18")
private int age;

@NotBlank(message = "Address is required")
 private String adress;

    public Customer(String name, String email, int age, String adress) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
