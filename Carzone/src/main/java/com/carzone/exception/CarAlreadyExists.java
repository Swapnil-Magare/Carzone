package com.carzone.exception;

public class CarAlreadyExists extends RuntimeException {
    public CarAlreadyExists(String message) {
        super(message);
    }
}
