package com.example.cloudlab.service;

import com.example.cloudlab.data.Car;

import java.util.ArrayList;
import java.util.List;

public interface RentalService {

    public List<Car> getCars();

    public Car getCar(String plateNumber) throws CarNotFoundException;

    public void rent(String plateNumber, String begin, String end) throws CarNotFoundException;

    public void getBack(String plateNumber) throws CarNotFoundException;
}
