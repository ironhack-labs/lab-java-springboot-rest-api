package model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email is required")
    private String email;
    @Min(value=18,message="Age is minimum 18")
    private int age;
    @NotBlank(message = "Address is required")
    private String address;
    public Customer(String name,String email,int age,String address){
        this.name=name;
        this.email=email;
        this.age=age;
        this.address=address;
    }
    public Customer() {
    }
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
