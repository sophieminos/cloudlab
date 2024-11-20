package com.example.cloudlab.web;

import com.example.cloudlab.data.Car;
import com.example.cloudlab.data.Dates;
import com.example.cloudlab.service.CarNotFoundException;
import com.example.cloudlab.service.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RentalWebService {

    RentalService rentalService;
    Logger logger = LoggerFactory.getLogger(RentalWebService.class);
    @Autowired
    public RentalWebService(RentalService r){
        this.rentalService = r;
    }
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> listOfCars(){
        logger.info("Get list of cars.");
        return rentalService.getCars();
    }

    @GetMapping("/car/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(
            @PathVariable("plateNumber") String plateNumber) throws CarNotFoundException {
        logger.info("Get car.");
        return rentalService.getCar(plateNumber);
    }

    @PutMapping(value = "/car/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = true)boolean rent,
            @RequestBody(required = false) Dates dates) throws Exception, CarNotFoundException {
        if (dates==null || dates.getBegin() == null || dates.getEnd() == null) {
            rentalService.getBack(plateNumber);
            logger.info("Car returned.");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String begin = simpleDateFormat.format(simpleDateFormat.parse(dates.getBegin()));
            String end = simpleDateFormat.format(simpleDateFormat.parse(dates.getEnd()));
            rentalService.rent(plateNumber, begin, end);
            logger.info("Car rented.");
        }
    }
}
