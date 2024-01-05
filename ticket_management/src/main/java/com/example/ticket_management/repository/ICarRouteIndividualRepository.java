package com.example.ticket_management.repository;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICarRouteIndividualRepository extends JpaRepository<CarRouteIndividual,Integer> {
    @Query(value = "select car_route_individual.end_time as endTime, \n" +
            "car_route_individual.start_time as startTime, \n" +
            "car.total_seats as totalSeats, \n" +
            "car_route_individual.price as price, \n" +
            "car_route.ending_point as endingPoint, \n" +
            "car_route.starting_point as startingPoint \n" +
            "from car_route_individual join car on car_route_individual.id = car.id\n" +
            "join car_route on car_route.id = car_route_individual.id\n" +
            "where car_route_individual.id = :idCRI", nativeQuery = true)
    ICarRouteIndividualDTO findByDTO(Integer idCRI);
}
