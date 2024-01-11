package com.example.ticket_management.service;

import com.example.ticket_management.model.Car;
import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.common.IGenerationService;

import java.util.List;

public interface ICarService extends IGenerationService<Car> {
    List<Car> findAllCarFree(String timeConvert);

    List<Car> findAllCarFreeByTime(String startTimeConvert, String endTimeConvert);

}
