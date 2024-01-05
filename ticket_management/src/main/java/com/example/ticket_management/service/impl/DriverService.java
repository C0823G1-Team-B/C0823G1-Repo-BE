package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.repository.IDriverRepository;
import com.example.ticket_management.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService implements IDriverService {
    @Autowired
    private IDriverRepository iDriverRepository;

    @Override
    public Iterable<Driver> findAll() {
        return iDriverRepository.findAll();
    }

    @Override
    public Driver save(Driver driver) {
        return iDriverRepository.save(driver);
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return iDriverRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iDriverRepository.deleteById(id);
    }
}
