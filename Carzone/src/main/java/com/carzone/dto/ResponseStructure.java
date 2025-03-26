package com.carzone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ResponseStructure<T> {
    private int statusCode;
    private String message;
    private T t;
}
