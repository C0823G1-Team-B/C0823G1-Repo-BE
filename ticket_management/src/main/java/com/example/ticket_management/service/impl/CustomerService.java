package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Customer;
import com.example.ticket_management.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
