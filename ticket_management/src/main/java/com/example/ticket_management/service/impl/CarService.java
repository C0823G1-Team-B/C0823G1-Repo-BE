package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Car;
import com.example.ticket_management.model.Driver;
import com.example.ticket_management.repository.ICarRepository;
import com.example.ticket_management.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository iCarRepository;

    @Override
    public Iterable<Car> findAll() {
        return iCarRepository.findAll();
    }

    @Override
    public Car save(Car car) {
        return iCarRepository.save(car);
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return iCarRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iCarRepository.deleteById(id);
    }

    @Override
    public List<Car> findAllCarFree(String timeConvert) {
        return iCarRepository.findAllCarFree(timeConvert);
    }

    @Override
    public List<Car> findAllCarFreeByTime(String startTimeConvert, String endTimeConvert) {
        return iCarRepository.findAllCarFreeByTime(startTimeConvert,endTimeConvert);
    }

    @Override
    public List<Car> findAllCarFreeByTimeUp(String startTimeConvert, String endTimeConvert) {
        return iCarRepository.findAllCarFreeByTimeUp(startTimeConvert,endTimeConvert);
    }

}
