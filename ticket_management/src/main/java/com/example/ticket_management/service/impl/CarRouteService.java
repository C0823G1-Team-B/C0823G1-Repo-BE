package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.service.ICarRouteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRouteService implements ICarRouteService {
    @Override
    public Iterable<CarRoute> findAll() {
        return null;
    }

    @Override
    public CarRoute save(CarRoute carRoute) {
        return null;
    }

    @Override
    public Optional<CarRoute> findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
