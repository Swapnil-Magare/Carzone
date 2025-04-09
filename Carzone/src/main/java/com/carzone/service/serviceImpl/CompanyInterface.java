package com.carzone.service.serviceImpl;


import com.carzone.dto.CompanyDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyInterface {

    public ResponseEntity<ResponseStructure<Company>> addCompany(CompanyDto companyDto);

    public ResponseEntity<ResponseStructure<List<Company>>> getAllCompany(int pageNumber, int pageSize);

    public ResponseEntity<ResponseStructure<Company>> getCompanyById(long id);

    public ResponseEntity<ResponseStructure<Company>> getCompanyByName(String name);

    public ResponseEntity<ResponseStructure<Company>> updateCompanyName(long id, CompanyDto companyDtores);

    public ResponseEntity<ResponseStructure<CompanyDto>> deleteCompany(long id);
}
