package com.ironhack.labjavaspringbootrestapisolutions.exception;

public class ProductException extends RuntimeException{
    public ProductException(String name){
        super("There is no product with name : " + name + ".\n" +
                "Please try another product!");
    }
}
