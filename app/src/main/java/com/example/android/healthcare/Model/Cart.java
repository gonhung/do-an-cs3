package com.example.android.healthcare.Model;

public class Cart {

    private  String username;
    private  String product;
    private  float price;
    private  String otype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public Cart() {
    }

    public Cart(String username, String product, float price, String otype) {
        this.username = username;
        this.product = product;
        this.price = price;
        this.otype = otype;
    }
}
