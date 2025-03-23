package com.carzone.dto;

import com.carzone.model.Company;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CarDto {
    private String model;
    private int year;
    private Company company;

}
