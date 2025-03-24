package com.carzone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String location;

    // One company can have many cars
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    private List<Car> cars;
}