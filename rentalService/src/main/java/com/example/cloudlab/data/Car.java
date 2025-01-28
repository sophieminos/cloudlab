package com.example.cloudlab.data;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String plateNumber;
    private String brand;
    private double price;

    private boolean rent;

    private Car cars;

    private List<Dates> dates;

    public Car(String plateNumber, String brand, double price){
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rent = false;
        this.dates = new ArrayList<Dates>();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public boolean getRent() {
        return this.rent;
    }

    public void setPlateNumber(String plateNumber){
        this.plateNumber = plateNumber;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public void rent(String begin, String end) {
        dates.add(new Dates(begin, end));
        setRent(true);
    }

    public void getBack() {
        setRent(false);
    }
}
