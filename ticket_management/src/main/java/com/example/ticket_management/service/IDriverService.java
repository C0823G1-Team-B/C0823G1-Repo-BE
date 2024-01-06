package com.example.ticket_management.service;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDriverService extends IGenerationService<Driver> {
    Page<Driver> find(Pageable pageable,String name);
}
