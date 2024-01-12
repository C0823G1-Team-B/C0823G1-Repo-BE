package com.example.ticket_management.service;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDriverService extends IGenerationService<Driver> {
    List<Driver> findAllDriverFree(String timeConvert);

    List<Driver> findAllDriverFreeByTime(String startTimeConvert, String endTimeConvert);
    Page<Driver> find(Pageable pageable, String name, Integer isRemove);
    Driver findIddr(Integer id);

}
