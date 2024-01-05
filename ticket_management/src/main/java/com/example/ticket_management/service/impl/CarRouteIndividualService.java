package com.example.ticket_management.service.impl;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.repository.ICarRouteIndividualRepository;
import com.example.ticket_management.service.ICarRouteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

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
        return null;
    }

    @Override
    public Optional<CarRouteIndividual> findById(Integer id) {
        return iCarRouteIndividualRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public ICarRouteIndividualDTO findByIdDTO(Integer idCRI) {
        return iCarRouteIndividualRepository.findByDTO(idCRI);
    }
}
