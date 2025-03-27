package com.carzone.exception;

import lombok.Data;

@Data
public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound(String message) {
        super(message);
    }
}
