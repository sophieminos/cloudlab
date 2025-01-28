package com.example.cloudlab.service;

import com.example.cloudlab.data.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    List<Car> cars = new ArrayList<>(List.of(
            new Car("ABCD", "Citroen", 10000),
            new Car("EFGH", "Renault", 11000),
            new Car("IJKL", "Peugeot", 12000)
    ));

    @Override
    public List<Car> getCars(){
        return this.cars;
    }
    @Override
    public Car getCar(String plateNumber) throws CarNotFoundException {
        if(plateNumber == null){
            throw new CarNotFoundException("none");
        }
        for(Car car : this.cars){
            if(car.getPlateNumber().equals(plateNumber)){
                return car;
            }
        }
        throw new CarNotFoundException(plateNumber);
    }

    @Override
    public void rent(String plateNumber, String begin, String end) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        car.rent(begin, end);
        System.out.println(car.getRent());
        updateCars(plateNumber, car);
    }

    @Override
    public void getBack(String plateNumber) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        car.getBack();
        System.out.println(car.getRent());
        updateCars(plateNumber, car);
    }

    public void updateCars(String plateNumber, Car car){
        removeCar(plateNumber);
        addCar(car);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(String plateNumber) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        cars.remove(car);
    }


}
