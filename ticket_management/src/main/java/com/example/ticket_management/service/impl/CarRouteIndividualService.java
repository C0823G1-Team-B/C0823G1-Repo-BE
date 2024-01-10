package com.example.ticket_management.service.impl;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;

import com.example.ticket_management.repository.ICarRouteIndividualRepository;
import com.example.ticket_management.service.ICarRouteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public Page<ICarRouteIndividualDTO> findAllDTO(Pageable pageable) {
        return iCarRouteIndividualRepository.findAllDTO(pageable);
    }

    @Override
    public Iterable<ICarRouteIndividualDTO> findAllByRevenue() {
        return iCarRouteIndividualRepository.findAllByRevenue();
    }

    @Override
    public ICarRouteIndividualDTO findByIdDTO(Integer idCRI) {
        return iCarRouteIndividualRepository.findByDTO(idCRI);
    }

//    @Override
//    public List<CarRouteIndividual> findCarRouteIndividualByStartTimeAndCarRoute_Id(LocalDateTime startTime, Integer id) {
//        return iCarRouteIndividualRepository.findCarRouteIndividualByStartTimeAndCarRoute_Id(startTime,id);
//    }

    @Override
    public List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(String timeConvert, Integer id) {
        return iCarRouteIndividualRepository.findCarouteByStartTimeAndIdRoute(timeConvert,id);
    }
}
