package com.SpringRestAPI.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Customer {

    @NotBlank(message = "The Name cannot be blank")
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters")
    private String customerName;

    @Email(message = "Please enter a valid e-mail address")
    private String customerEmail;

    @Min(value = 18, message = "Age must be at least 18")
    private int customerAge;

    @NotBlank(message = "The Address cannot be blank")
    private String customerAddress;


    /// @NotBlank: Ensures that the annotated string is not null or consists of only whitespace characters.
    /// This is useful for fields like name and address to ensure that they are filled with meaningful data.

    /// @Size: Validates that the annotated string's length is within the specified range (min and max).
    /// This helps enforce constraints on text fields, such as ensuring a name is neither too short nor too long.

    /// @Email: Validates that the annotated string is a well-formed email address.
    /// This is crucial for preventing invalid email formats in your system.

    /// @Min: Checks that the annotated integer is at least a specified value.
    /// In this case it is 18. Which is often used for age restrictions.


    public Customer(String customerName, String customerEmail, int customerAge, String customerAddress) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAge = customerAge;
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}




