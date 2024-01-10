package com.example.ticket_management.repository;

import com.example.ticket_management.dto.ITicketDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITicketRepository extends JpaRepository<Ticket,Integer> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    @Query(value = "select ticket.id as id,\n" +
            "ticket.number_seat as numberSeat,\n" +
            "ticket.status as status,\n" +
            "customer.name as customerName,\n" +
            "payment.status as paymentStatus,\n" +
            "ticket.price as price,\n" +
            "car_route.starting_point as startingPoint,\n" +
            "car_route.ending_point as endingPoint\n" +
            "from ticket left join customer on ticket.customer_id = customer.id\n" +
            "left join payment on ticket.payment_id = payment.id \n" +
            "left join car_route_individual on ticket.car_route_individual_id = car_route_individual.id\n" +
            "left join car_route on car_route.id = car_route_individual.car_route_id\n" +
            "where ticket.car_route_individual_id = :idCRI",nativeQuery = true)
    Page<ITicketDTO> findAllByIdCRI(@Param("idCRI") Integer idCRI, Pageable pageable);
}
