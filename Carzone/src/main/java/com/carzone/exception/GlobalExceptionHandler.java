package com.carzone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CarAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 400
    public @ResponseBody ErrorResponse handleCarAlreadyExistsException(CarAlreadyExists ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = CarNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // HTTP 404
    public @ResponseBody ErrorResponse handleCarNotFoundException(CarNotFound ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // You can add more exception handlers as needed
}
