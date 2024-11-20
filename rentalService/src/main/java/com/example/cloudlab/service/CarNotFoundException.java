package com.example.cloudlab.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException  {
    public CarNotFoundException(String plateNumber) {
        super("Car not found with plate number: " + plateNumber);
    }
}
