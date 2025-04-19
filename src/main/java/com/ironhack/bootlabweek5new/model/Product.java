package com.ironhack.bootlabweek5new.model;


public class Product {

       private String name;
       private double price;
       private String category;
       private int quantity;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }


        public Product(String name, double price, String category, int quantity) {
                this.name = name;
                this.price = price;
                this.category = category;
                this.quantity = quantity;
        }
}
