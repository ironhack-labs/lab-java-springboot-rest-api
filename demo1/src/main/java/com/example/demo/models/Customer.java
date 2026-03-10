package com.example.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank
    private String name;
@Email
    private String email;
@Min(18)
private  int age;
@NotBlank
private String adress;

public Customer(){}

    public  Customer(String name, String email, int age, String adress){
    this.name=name;
    this.email=email;
    this.age=age;
    this.adress=adress;
    }

    public void setName(String name){
    this.name=name;
    }

    public void setEmail(String email){
    this.email=email;
    }

    public void setAge(int age){
    this.age=age;
    }

    public void setAdress(String adress){
    this.adress=adress;
    }

    public String getName(){
    return name;
    }

    public String getEmail(){
    return  email;
    }

    public String getAdress() {
        return adress;
    }

    public int getAge() {
        return age;
    }
}
