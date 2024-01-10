package com.example.ticket_management.service;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ICarRouteIndividualService extends IGenerationService<CarRouteIndividual> {
//    List<CarRouteIndividual> findCarRouteIndividualByStartTimeAndCarRoute_Id(LocalDateTime startTime , Integer id);


    List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(String timeConvert, Integer id);
    ICarRouteIndividualDTO findByIdDTO(Integer idCRI);

    Page<ICarRouteIndividualDTO> findAllDTO(Pageable pageable);

    Iterable<ICarRouteIndividualDTO> findAllByRevenue();
}
