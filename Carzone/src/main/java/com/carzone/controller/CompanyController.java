package com.carzone.controller;

import com.carzone.dto.CompanyDto;
import com.carzone.model.Car;
import com.carzone.model.Company;
import com.carzone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @PostMapping("/add")
    public String addCompany(@RequestBody Company company) {
        if (company.getCars() != null) {
            for (Car car : company.getCars()) {
                car.setCompany(company);
            }
        }
        companyService.addCompany(company);
        return "Company and cars saved successfully!";
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(@PathVariable long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/update/{id}")
    public CompanyDto updateCompanyByName(@PathVariable long id, @RequestBody CompanyDto company) {

        return companyService.updateCompanyName(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompanyById(@PathVariable long id) {

        companyService.deleteCompany(id);
    }

    public void delete(){

    }

}
