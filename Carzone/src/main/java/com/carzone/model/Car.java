package com.carzone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;


    @Column(name = "car_model")
    private String model;


    @Column(name = "car_year")
    private int year;


    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnoreProperties("cars")
    private Company company;
}