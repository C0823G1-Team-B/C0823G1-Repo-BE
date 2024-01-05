package com.example.ticket_management.repository;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRouteIndividualRepository extends JpaRepository<CarRouteIndividual,Integer> {

    ICarRouteIndividualDTO findByIdDTO(Integer idCRI);
}
