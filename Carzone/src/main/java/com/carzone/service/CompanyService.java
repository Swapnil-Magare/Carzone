package com.carzone.service;

import com.carzone.dto.CarDto;
import com.carzone.dto.CompanyDto;
import com.carzone.model.Car;
import com.carzone.model.Company;
import com.carzone.repositoy.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepositoy;

    private Map<String, Company> companyMap = new HashMap<>();


    public ResponseEntity<String> addCompany(CompanyDto companyDto) {
        if (companyMap.containsKey(companyDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company name Must be Unique!");
        }

        if (companyRepositoy.findByName(companyDto.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company name Must be Unique!");
        }

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setLocation(companyDto.getLocation());

        List<Car> carList = new ArrayList<>();
        for(CarDto carDto : companyDto.getCars()){
            Car car =new Car();
            car.setModel(carDto.getModel());
            car.setYear(carDto.getYear());
            car.setCompany(carDto.getCompany());
            carList.add(car);
        }
        company.setCars(carList);

        companyRepositoy.save(company);

        companyMap.put(companyDto.getName(),company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company and Car added successfully!");

    }


    public ResponseEntity<String> deleteCompany(Long id) {
        if (companyRepositoy.existsById(id)) {
            companyRepositoy.deleteById(id);
            return ResponseEntity.ok("Company deleted Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found!");
        }
    }

//    public ResponseEntity<String> deleteCompany(String name){
//        if (companyRepositoy.deleteByName(name)-> {
//            return ResponseEntity.ok("Company deleted Successfully!");
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found!");
//        }
//    }
}
