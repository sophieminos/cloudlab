package com.example.cloudlab.service;

import com.example.cloudlab.data.Car;
import com.example.cloudlab.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final CarRepository carRepository;
    /*
    List<Car> cars = new ArrayList<>(List.of(
            new Car("ABCD", "Citroen", 10000),
            new Car("EFGH", "Renault", 11000),
            new Car("IJKL", "Peugeot", 12000),
            new Car("MNOP", "Peugeot", 13000),
            new Car("QRST", "Citroen", 14000),
            new Car("UVWX", "Renault", 15000)));
    */
    @Autowired
    public RentalServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();  // Utilisation du repository pour récupérer toutes les voitures
    }

    @Override
    public Car getCar(String plateNumber) throws CarNotFoundException {
        Car car = carRepository.findByPlateNumber(plateNumber);
        if (car == null) {
            throw new CarNotFoundException(plateNumber);
        }
        return car;
    }

    @Override
    public void rent(String plateNumber, String begin, String end) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        car.rent(begin, end);
        carRepository.save(car);  // Sauvegarder la voiture mise à jour dans la base de données
    }

    @Override
    public void getBack(String plateNumber) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        car.getBack();
        carRepository.save(car);  // Sauvegarder la voiture retournée dans la base de données
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);  // Ajouter la voiture dans la base de données
    }

    public void removeCar(String plateNumber) throws CarNotFoundException {
        Car car = getCar(plateNumber);
        carRepository.delete(car);  // Supprimer la voiture de la base de données
    }
}
