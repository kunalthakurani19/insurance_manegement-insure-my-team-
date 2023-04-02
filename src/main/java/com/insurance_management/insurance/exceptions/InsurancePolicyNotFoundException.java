package com.insurance_management.insurance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsurancePolicyNotFoundException extends RuntimeException {
    public InsurancePolicyNotFoundException(String message) {
        super(message);
    }
}