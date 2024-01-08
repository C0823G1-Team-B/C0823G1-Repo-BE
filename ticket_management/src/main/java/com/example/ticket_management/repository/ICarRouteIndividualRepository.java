package com.example.ticket_management.repository;

import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ICarRouteIndividualRepository extends JpaRepository<CarRouteIndividual,Integer> {
    List<CarRouteIndividual> findCarRouteIndividualByStartTimeAndCarRoute_Id(LocalDateTime startTime, Integer id);

//    @Query(value = "select cdt.* " +
//            "from car_route_individual cdt " +
//            "where cdt.start_time >= :timeConvert and cdt.car_route_id = :carRoute",nativeQuery = true)

    @Query(value = "select cdt.* " +
            "from car_route_individual cdt " +
            "where cdt.start_time >= :timeConvert " +
            "and cdt.start_time < date_add(:timeConvert, interval 1 day) " +
            "and cdt.car_route_id = :carRoute", nativeQuery = true)
    List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(@Param("timeConvert") String timeConvert, @Param("carRoute") Integer carRoute);


//    List<CarRouteIndividual> findCarouteByStartTimeAndIdRoute(String timeConvert, Integer id);

    ICarRouteIndividualDTO findByIdDTO(Integer idCRI);
}
