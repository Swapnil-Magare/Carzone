package com.carzone.controller;

import com.carzone.dto.CompanyDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.model.Company;
import com.carzone.repositoy.CompanyRepository;
import com.carzone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<Company>> addCompany(@RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Company>>> getAllCompanies() {
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Company>> getCompanyById(@PathVariable long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseStructure<Company>> getCompanyByName(@PathVariable String name){
        return companyService.getCompanyByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Company>> updateCompanyByName(@PathVariable long id, @RequestBody CompanyDto company) {
        return companyService.updateCompanyName(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<CompanyDto>> deleteCompanyById(@PathVariable long id) {
        return companyService.deleteCompany(id);
    }
}