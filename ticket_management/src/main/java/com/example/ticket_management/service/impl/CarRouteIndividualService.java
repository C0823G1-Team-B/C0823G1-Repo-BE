package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.repository.ICarRouteIndividualRepository;
import com.example.ticket_management.service.ICarRouteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRouteIndividualService implements ICarRouteIndividualService {
    @Autowired
    private ICarRouteIndividualRepository iCarRouteIndividualRepository;
    @Override
    public Iterable<CarRouteIndividual> findAll() {
        return iCarRouteIndividualRepository.findAll();
    }

    @Override
    public CarRouteIndividual save(CarRouteIndividual carRouteIndividual) {
        return iCarRouteIndividualRepository.save(carRouteIndividual);
    }

    @Override
    public Optional<CarRouteIndividual> findById(Integer id) {
        return iCarRouteIndividualRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iCarRouteIndividualRepository.deleteById(id);
    }
}
