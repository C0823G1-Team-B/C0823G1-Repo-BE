package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.IDriverService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService implements IDriverService {
    @Override
    public Iterable<Driver> findAll() {
        return null;
    }

    @Override
    public Driver save(Driver driver) {
        return null;
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
