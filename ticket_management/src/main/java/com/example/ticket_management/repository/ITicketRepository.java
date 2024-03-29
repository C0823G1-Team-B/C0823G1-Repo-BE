package com.example.ticket_management.repository;

import com.example.ticket_management.dto.*;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    @Query(value = "select ticket.id as id,\n" +
            "ticket.number_seat as numberSeat,\n" +
            "ticket.status as status,\n" +
            "ticket.is_delete as isDelete,\n" +
            "customer.name as customerName,\n" +
            "payment.status as paymentStatus,\n" +
            "ticket.price as price,\n" +
            "car_route.starting_point as startingPoint,\n" +
            "car_route.ending_point as endingPoint\n" +
            "from ticket left join customer on ticket.customer_id = customer.id\n" +
            "left join payment on ticket.payment_id = payment.id \n" +
            "left join car_route_individual on ticket.car_route_individual_id = car_route_individual.id\n" +
            "left join car_route on car_route.id = car_route_individual.car_route_id\n" +
            "where ticket.car_route_individual_id = :idCRI", nativeQuery = true)
    Page<ITicketDTO1> findAllByIdCRI(@Param("idCRI") Integer idCRI, Pageable pageable);

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


    @Procedure
    void setTicketIsDelete();

    @Query(value = "select count(ticket.id)\n" +
            "from ticket\n" +
            "where ticket.car_route_individual_id = :idCi and ticket.status = :status",nativeQuery = true)
    Integer findAllTicketByCiIdAndStatus(@Param("idCi")Integer idCi,@Param("status")Integer status );

    @Query(value = "SELECT ticket.customer_id as customerId\n" +
            "FROM ticket\n" +
            "WHERE ticket.car_route_individual_id = :idCi AND ticket.status = 1\n" +
            "group by ticket.customer_id",nativeQuery = true)
    List<CusDTO> findAllTicketByCRI(@Param("idCi") Integer idCRI);

    @Query(value = "select ticket.id as id,\n" +
            "ticket.number_seat as numberSeat,\n" +
            "ticket.status as status,\n" +
            "ticket.is_delete as isDelete,\n" +
            "car_route_individual.start_time as startTime,\n" +
            "car_route_individual.end_time as endTime,\n" +
            "ticket.price as price,\n" +
            "car_route.starting_point as startingPoint,\n" +
            "car_route.ending_point as endingPoint\n" +
            "from ticket \n" +
            "left join car_route_individual on ticket.car_route_individual_id = car_route_individual.id\n" +
            "left join car_route on car_route.id = car_route_individual.car_route_id\n" +
            "where ticket.id = :id", nativeQuery = true)
    ITicketDTO1 getITicketDTO1ById(Integer id);
    @Query(value = "\n" +
            "SELECT NEW com.example.ticket_management.dto.CustomerDTO(ticket.customer_id, ticket.car_route_individual_id)\n" +
            "FROM Ticket ticket\n" +
            "WHERE ticket.car_route_individual_id = :idCRI AND ticket.status = 1\n" +
            "GROUP BY ticket.customer_id",nativeQuery = true)
    List<CustomerDTO> findAllTicketByCRIUpdate(@Param("idCRI") Integer idCRI);
}
