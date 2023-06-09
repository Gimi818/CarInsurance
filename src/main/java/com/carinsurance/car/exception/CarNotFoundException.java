package com.carinsurance.car.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super(String.format("Car with id %d not found", id));
    }
}
