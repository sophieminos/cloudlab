package com.example.cloudlab.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identifiant unique pour JPA

    private String plateNumber;
    private String brand;
    private double price;
    private boolean rent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "car_id")  // Relier les dates au car
    private List<Dates> dates = new ArrayList<>();

    public Car() {}

    public Car(String plateNumber, String brand, double price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rent = false;
    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public boolean getRent() {
        return this.rent;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setPlateNumber(String plateNumber){
        this.plateNumber = plateNumber;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public void rent(String begin, String end) {
        dates.add(new Dates(this.id, begin, end));
        setRent(true);
    }

    public void getBack() {
        datesRepository.deleteByCarId(this.id);
        setRent(false);
    }
}
