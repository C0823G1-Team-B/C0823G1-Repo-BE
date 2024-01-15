package com.example.ticket_management.repository;

import com.example.ticket_management.model.CarRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRouteRepository extends JpaRepository<CarRoute,Integer> {
    CarRoute findCarRouteByStartingPointAndEndingPoint(String startingPoint , String endingPoint);


}
