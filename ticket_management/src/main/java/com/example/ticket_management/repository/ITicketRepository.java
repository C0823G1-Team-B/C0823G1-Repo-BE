package com.example.ticket_management.repository;

import com.example.ticket_management.dto.ITicketDto;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    @Query(value = "select t.number_seat as numberSeat,t.price, c.name,cri.start_time as startTime,cri.end_time as endTime,car.license_plates as licensePlates, cr.starting_point as startingPoint,cr.ending_point as endingPoint \n" +
            "from ticket t " +
            "left join customer c on t.customer_id = c.id " +
            "left join car_route_individual cri on t.car_route_individual_id = cri.id " +
            "left join car on cri.car_id = car.id " +
            "left join car_route cr on cri.car_route_id = cr.id " +
            "where c.email like :name ",
            nativeQuery = true, countQuery = "select count(*) from(select t.number_seat,t.price, c.name,cri.start_time,cri.end_time,car.license_plates, cr.starting_point,cr.ending_point\\n\" +\n" +
            "            from ticket t " +
            "            left join customer c on t.customer_id = c.id " +
            "            left join car_route_individual cri on t.car_route_individual_id = cri.id " +
            "            left join car on cri.car_id = car.id " +
            "            left join car_route cr on cri.car_route_id = cr.id " +
            "            where c.email like :name ) temp")
    Page<ITicketDto> findAllTicketInformationOfUser(Pageable pageable, @Param("name") String email);
}
