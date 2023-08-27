package com.flipkart.inventorymanagementsystem.model;

public class Address {
    private String address;
    private int zipcode;
    private String state;
    private String country;

    public Address(String address, int zipcode, String state, String country) {
        this.address = address;
        this.zipcode = zipcode;
        this.state = state;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
