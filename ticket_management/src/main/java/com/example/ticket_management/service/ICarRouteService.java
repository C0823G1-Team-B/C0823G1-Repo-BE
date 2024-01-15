package com.example.ticket_management.service;

import com.example.ticket_management.dto.CarRouteDTO;
import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.service.common.IGenerationService;

import java.util.List;

public interface ICarRouteService extends IGenerationService<CarRoute> {
    CarRoute findCarRouteByStartingPointAndEndingPoint(String departure, String destination);



    List<CarRouteDTO> getCarRouteHigh();
}
