package com.hust.alt.model;

public class Order {

    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private int pQuantity;
    private double totalMoney;
    private int productId;

    public Order() {
    }

    public Order(int id, String name, String phoneNumber, String email, String address, int pQuantity, double totalMoney, int productId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.pQuantity = pQuantity;
        this.totalMoney = totalMoney;
        this.productId = productId;
    }
    public Order( String name, String phoneNumber, String email, String address, int pQuantity, double totalMoney, int productId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.pQuantity = pQuantity;
        this.totalMoney = totalMoney;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getIdProduct() {
        return productId;
    }

    public void setIdProduct(int productId) {
        this.productId = productId;
    }
}
