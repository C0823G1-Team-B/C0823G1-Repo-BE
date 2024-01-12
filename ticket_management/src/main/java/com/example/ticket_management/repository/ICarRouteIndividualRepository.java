package com.example.ticket_management.repository;

import com.example.ticket_management.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ICarRouteIndividualRepository extends JpaRepository<CarRouteIndividual,Integer> {
    @Query(value = "select car_route_individual.id as id, \n" +
            "car_route_individual.end_time as endTime, \n" +
            "car_route_individual.start_time as startTime, \n" +
            "car.total_seats as totalSeats, \n" +
            "car_route_individual.price as price, \n" +
            "car_route.ending_point as endingPoint, \n" +
            "car_route.starting_point as startingPoint \n" +
            "from car_route_individual join car on car_route_individual.car_id = car.id\n" +
            "join car_route on car_route.id = car_route_individual.car_route_id\n" +
            "where car_route_individual.id = :idCRI", nativeQuery = true)
    ICarRouteIndividualDTO findByDTO(Integer idCRI);

    List<CarRouteIndividual> findCarRouteIndividualByStartTimeAndCarRoute_Id(LocalDateTime startTime, Integer id);

//    @Query(value = "select cdt.* " +
//            "from car_route_individual cdt " +
//            "where cdt.start_time >= :timeConvert and cdt.car_route_id = :carRoute",nativeQuery = true)

    @Query(value = "SELECT cdt.* " +
            "FROM car_route_individual cdt " +
            "WHERE cdt.start_time >= :timeConvert " +
            "AND cdt.start_time < DATE_ADD(DATE(:timeConvert), INTERVAL 1 DAY) " +
            "AND cdt.car_route_id = :carRoute", nativeQuery = true)
    List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(@Param("timeConvert") String timeConvert, @Param("carRoute") Integer carRoute);




//    List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(String timeConvert, Integer id);

    @Query(value = "select car_route_individual.id as id, \n" +
            "car_route_individual.end_time as endTime, \n" +
            "car_route_individual.start_time as startTime, \n" +
            "car.total_seats as totalSeats, \n" +
            "car_route_individual.price as price, \n" +
            "car_route.ending_point as endingPoint, \n" +
            "car_route.starting_point as startingPoint,\n" +
            "driver.name as driverName,\n" +
            "car.license_plates as licensePlates\n" +
            "from car_route_individual join car on car_route_individual.car_id = car.id\n" +
            "join car_route on car_route.id = car_route_individual.car_route_id\n" +
            "join driver on driver.id = car_route_individual.driver_id\n" +
            "where start_time > CURRENT_TIMESTAMP or (start_time < CURRENT_TIMESTAMP and end_time > CURRENT_TIMESTAMP)\n" +
            "order by startTime;" , nativeQuery = true)
    Page<ICarRouteIndividualDTO> findAllDTO(Pageable pageable);

    @Query(value = "SELECT COUNT(CASE WHEN ticket.status = true THEN 1 ELSE NULL END) AS sold,\n" +
            "       car_route_individual.end_time AS endTime,\n" +
            "       car_route_individual.start_time AS startTime,\n" +
            "       car.total_seats AS totalSeats,\n" +
            "       car_route_individual.price AS price,\n" +
            "       car_route.ending_point AS endingPoint,\n" +
            "       car_route.starting_point AS startingPoint,\n" +
            "       driver.name AS driverName,\n" +
            "       car.license_plates AS licensePlates\n" +
            "FROM car_route_individual\n" +
            "JOIN car ON car_route_individual.car_id = car.id\n" +
            "JOIN car_route ON car_route.id = car_route_individual.car_route_id\n" +
            "JOIN driver ON driver.id = car_route_individual.driver_id\n" +
            "JOIN ticket ON ticket.car_route_individual_id = car_route_individual.id\n" +
            "WHERE car_route_individual.end_time < CURRENT_TIMESTAMP\n" +
            "GROUP BY car_route_individual.end_time,\n" +
            "         car_route_individual.start_time,\n" +
            "         car.total_seats,\n" +
            "         car_route_individual.price,\n" +
            "         car_route.ending_point,\n" +
            "         car_route.starting_point,\n" +
            "         driver.name,\n" +
            "         car.license_plates\n" +
            "ORDER BY car_route_individual.end_time;", nativeQuery = true)
    Iterable<ICarRouteIndividualDTO> findAllByRevenue();
  
    @Query(value = "select * \n" +
            "from car_route_individual cri\n" +
            "where cri.start_time >= :timeConvert ",nativeQuery = true)
    List<CarRouteIndividual> findAllIndividualByStartTime(@Param("timeConvert") String timeConvert);
    @Modifying
    @Transactional
    @Query(value = "UPDATE car_route_individual\n" +
            "SET car_route_individual.is_delete = 1\n" +
            "WHERE car_route_individual.id = :idCI",nativeQuery = true)
    void updateDeleteById(@Param("idCI") Integer idCI);

    @Query(value = "select cri.* from driver d left join car_route_individual cri on  d.id = cri.driver_id where d.is_delete = 0 and cri.driver_id is null and d.id = :id",nativeQuery = true)
    Iterable<CarRouteIndividual> findIdDriver(@Param("id") Integer id);

}
