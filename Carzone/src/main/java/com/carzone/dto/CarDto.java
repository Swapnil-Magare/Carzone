package com.carzone.dto;

import com.carzone.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private String model;
    private int year;
    private Long companyId;
}
