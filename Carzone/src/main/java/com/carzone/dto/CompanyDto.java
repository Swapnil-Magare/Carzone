package com.carzone.dto;

import lombok.Data;

import javax.swing.*;
import java.util.List;

@Data
public class CompanyDto {
    private String name;
    private String location;
    private List<CarDto> cars;
}
