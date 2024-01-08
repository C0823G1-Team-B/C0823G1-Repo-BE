package com.example.ticket_management.repository;

import com.example.ticket_management.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
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
    @Query(value = "select d.* from driver d where d.name like :name and d.is_delete = 0",
            nativeQuery = true,
            countQuery = "select count(*) from(select d.* from driver d where d.name like :name and d.is_delete = 0) temp")
    Page<Driver> find(Pageable pageable, @Param("name") String name);


    @Transactional
    @Modifying
    @Query(value = "update driver d set d.is_delete = true where d.id = :id ",nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
