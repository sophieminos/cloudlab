package com.example.cloudlab.repository;

import com.example.cloudlab.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Car findByPlateNumber(String plateNumber);
}
