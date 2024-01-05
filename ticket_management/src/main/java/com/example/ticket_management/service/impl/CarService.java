package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Car;
import com.example.ticket_management.service.ICarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Override
    public Iterable<Car> findAll() {
        return null;
    }

    @Override
    public Car save(Car car) {
        return null;
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
