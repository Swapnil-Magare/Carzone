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

    @GetMapping("/{id}")
    public Optional<Company> getById(@PathVariable Long id){
        return companyService.getByCompanyId(id);
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompany();
    }

    @PutMapping("/update/{id}")
    public CompanyDto updateById(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        return companyService.updateById(id, companyDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        companyService.deleteByCompanyid(id);
    }

}