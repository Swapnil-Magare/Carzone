package com.carzone.dto;

import com.carzone.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String name;
    private String location;
    private List<CarDto> cars;
}