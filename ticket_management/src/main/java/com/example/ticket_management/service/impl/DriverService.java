package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.repository.IDriverRepository;
import com.example.ticket_management.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements IDriverService {
    @Autowired
    private IDriverRepository iDriverRepository;

    @Override
    public Iterable<Driver> findAll() {
        return iDriverRepository.findAll();
    }

    @Override
    public Driver save(Driver driver) {
        return iDriverRepository.save(driver);
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return iDriverRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iDriverRepository.deleteById(id);
    }

    @Override
    public List<Driver> findAllDriverFree(String timeConvert) {
        return iDriverRepository.findAllDriverFree(timeConvert);
    }

    @Override
    public List<Driver> findAllDriverFreeByTime(String startTimeConvert, String endTimeConvert) {
        return iDriverRepository.findAllDriverFreeByTime(startTimeConvert, endTimeConvert);
    }

    @Override
    public Page<Driver> find(Pageable pageable, String name, Integer isRemove) {
        if (isRemove == 1) {
            return iDriverRepository.findFreeTimeDrivers(pageable, "%" + name + "%");
        }
        return iDriverRepository.find(pageable, "%" + name + "%");
    }

    @Override
    public Driver findIddr(Integer id) {
        return iDriverRepository.findById(id).get();
    }

    @Override
    public List<Driver> findAllDriverFreeByTimeUp(String startTimeConvert, String endTimeConvert) {
        return iDriverRepository.findAllDriverFreeByTimeUp(startTimeConvert,endTimeConvert);
    }
}
