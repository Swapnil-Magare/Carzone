package com.carzone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column
    private String model;

    @Column
    private int year;

    // Many cars can belong to one company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
