package com.carzone.serviceInterface;


import com.carzone.dto.CompanyDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CompanyInterface {

    public ResponseEntity<ResponseStructure<Company>> addCompany(CompanyDto companyDto);

    public ResponseEntity<ResponseStructure<Company>> getCompanyById(long id);

    public ResponseEntity<ResponseStructure<List<Company>>> getAllCompany();

    public ResponseEntity<ResponseStructure<Company>> updateCompanyName(long id, CompanyDto companyDtores);


    public ResponseEntity<ResponseStructure<CompanyDto>> deleteCompany(long id);
}
