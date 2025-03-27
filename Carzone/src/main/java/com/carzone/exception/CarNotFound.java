package com.carzone.exception;

public class CarNotFound extends RuntimeException {
    public CarNotFound(String message) {
        super(message);
    }
}