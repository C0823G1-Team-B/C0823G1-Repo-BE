package com.example.ticket_management.repository;

import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarRouteRepository extends JpaRepository<CarRoute,Integer> {
    CarRoute findCarRouteByStartingPointAndEndingPoint(String startingPoint , String endingPoint);

}
