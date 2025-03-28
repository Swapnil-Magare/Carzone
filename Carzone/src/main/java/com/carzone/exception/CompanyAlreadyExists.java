package com.carzone.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CompanyAlreadyExists extends RuntimeException {
    public CompanyAlreadyExists(String message) {
        super(message);
    }
}

