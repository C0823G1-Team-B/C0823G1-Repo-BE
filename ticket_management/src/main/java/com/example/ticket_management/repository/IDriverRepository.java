package com.example.ticket_management.repository;

import com.example.ticket_management.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDriverRepository extends JpaRepository<Driver,Integer> {
    @Query(value = "SELECT DISTINCT driver.* " +
            "FROM driver " +
            "WHERE driver.id NOT IN (" +
            "    SELECT cdl.driver_id " +
            "    FROM car_route_individual cdl " +
            "    WHERE (" +
            "        (cdl.start_time >= :timeConvert AND cdl.start_time <= :timeConvert) " +
            "        OR (cdl.end_time >= :timeConvert AND cdl.end_time <= :timeConvert) " +
            "        OR (cdl.start_time <= :timeConvert AND cdl.end_time >= :timeConvert) " +
            "    )" +
            ")",
            nativeQuery = true)
    List<Driver> findAllDriverFree(@Param("timeConvert") String timeStartConvert);

    @Query(value = "SELECT DISTINCT driver.* \n" +
            "FROM driver\n" +
            "WHERE driver.id NOT IN (\n" +
            "    SELECT cdl.driver_id\n" +
            "    FROM car_route_individual cdl\n" +
            "    WHERE (\n" +
            "        (cdl.start_time >= :timeStartConvert AND cdl.start_time <= :endTimeConvert)\n" +
            "        OR (cdl.end_time >= :timeStartConvert AND cdl.end_time <= :endTimeConvert)\n" +
            "        OR (cdl.start_time <= :timeStartConvert AND cdl.end_time >= :endTimeConvert)\n" +
            "    )\n" +
            ")",nativeQuery = true)
    List<Driver> findAllDriverFreeByTime(@Param("timeStartConvert") String startTimeConvert, @Param("endTimeConvert") String endTimeConvert);
}
