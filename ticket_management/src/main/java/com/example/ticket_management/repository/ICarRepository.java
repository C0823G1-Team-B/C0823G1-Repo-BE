package com.example.ticket_management.repository;

import com.example.ticket_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarRepository extends JpaRepository<Car,Integer> {
    @Query(value = "SELECT DISTINCT car.* " +
            "FROM car " +
            "WHERE car.id NOT IN (" +
            "    SELECT cdl.car_id " +
            "    FROM car_route_individual cdl " +
            "    WHERE (" +
            "        (cdl.start_time >= :timeConvert AND cdl.start_time <= :timeConvert) " +
            "        OR (cdl.end_time >= :timeConvert AND cdl.end_time <= :timeConvert) " +
            "        OR (cdl.start_time <= :timeConvert AND cdl.end_time >= :timeConvert) " +
            "    )" +
            ")",
            nativeQuery = true)

    List<Car> findAllCarFree(@Param("timeConvert") String timeConvert);

    @Query(value = "SELECT DISTINCT car.* \n" +
            "FROM car\n" +
            "WHERE car.id NOT IN (\n" +
            "    SELECT cdl.car_id\n" +
            "    FROM car_route_individual cdl\n" +
            "    WHERE (\n" +
            "        (cdl.start_time >= :timeStartConvert AND cdl.start_time <= :endTimeConvert)\n" +
            "        OR (cdl.end_time >= :timeStartConvert AND cdl.end_time <= :endTimeConvert)\n" +
            "        OR (cdl.start_time <= :timeStartConvert AND cdl.end_time >= :endTimeConvert)\n" +
            "    )\n" +
            ")",nativeQuery = true)
    List<Car> findAllCarFreeByTime(@Param("timeStartConvert") String startTimeConvert, @Param("endTimeConvert") String endTimeConvert);
}
