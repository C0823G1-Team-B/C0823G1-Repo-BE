package com.example.ticket_management.service;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.common.IGenerationService;

import java.util.List;

public interface IDriverService extends IGenerationService<Driver> {
    List<Driver> findAllDriverFree(String timeConvert);

    List<Driver> findAllDriverFreeByTime(String startTimeConvert, String endTimeConvert);
}
