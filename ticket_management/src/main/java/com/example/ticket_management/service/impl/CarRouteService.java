package com.example.ticket_management.service.impl;

import com.example.ticket_management.dto.CarRouteDTO;
import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.repository.ICarRouteRepository;
import com.example.ticket_management.service.ICarRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRouteService implements ICarRouteService {
    @Autowired
    private ICarRouteRepository iCarRouteRepository;

    @Override
    public List<CarRoute> findAll() {
        return iCarRouteRepository.findAll();
    }

    @Override
    public CarRoute save(CarRoute carRoute) {
        return iCarRouteRepository.save(carRoute);
    }

    @Override
    public Optional<CarRoute> findById(Integer id) {
        return iCarRouteRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iCarRouteRepository.deleteById(id);
    }

    @Override
    public CarRoute findCarRouteByStartingPointAndEndingPoint(String departure, String destination) {
         return iCarRouteRepository.findCarRouteByStartingPointAndEndingPoint(departure,destination);
    }

    @Override
    public List<CarRouteDTO> getCarRouteHigh() {
        return iCarRouteRepository.getCarRouteHigh();
    }


}
