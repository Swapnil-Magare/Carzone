package com.carzone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerCompany {
    @ExceptionHandler(value = CompanyAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
     public @ResponseBody ErrorResponse handleCompanyAlreadyExistsException(CompanyAlreadyExists ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());    //Return error response
     }

     @ExceptionHandler
     @ResponseStatus(HttpStatus.NOT_FOUND)
     public @ResponseBody ErrorResponse handleCompanyNotfoundException(CompanyNotFound companyNotFound){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), companyNotFound.getMessage());
     }
}
