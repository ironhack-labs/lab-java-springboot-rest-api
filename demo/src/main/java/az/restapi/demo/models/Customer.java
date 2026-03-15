package az.restapi.demo.models;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    private int counter = 1;
    private int customerId;

    @NotBlank
    private String name;

    @Email
    private String mail;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank
    private String address;

    public Customer(String name,String mail, int age, String address) {
        this.customerId = counter;
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    


}
