package models;
import jakarta.validation.constraints.*;

public class Customer {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(18)
    private int age;

    @NotBlank
    private String address;

    public Customer(){}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public int getAge(){return age;}
    public void setAge(int age){this.age=age;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}
}