package com.example.ticket_management.repository;

import com.example.ticket_management.dto.CarRouteDTO;
import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICarRouteRepository extends JpaRepository<CarRoute,Integer> {
    CarRoute findCarRouteByStartingPointAndEndingPoint(String startingPoint , String endingPoint);
    @Query(value = "select  car_route_individual.car_route_id as carRouteId\n" +
            "from car_route_individual\n" +
            "where date(start_time) between date_format(now(), '%Y-%m-01') and now()\n" +
            "group by car_route_individual.car_route_id\n" +
            "order by count(car_route_id) desc\n" +
            "limit 3;",nativeQuery = true)
    List<CarRouteDTO> getCarRouteHigh();

}
