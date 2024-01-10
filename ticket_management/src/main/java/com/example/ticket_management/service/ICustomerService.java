package com.example.ticket_management.service;

import com.example.ticket_management.model.Customer;
import com.example.ticket_management.service.common.IGenerationService;

import java.util.Optional;

public interface ICustomerService extends IGenerationService<Customer> {
    Optional<Customer> findByEmail(String email);
}
