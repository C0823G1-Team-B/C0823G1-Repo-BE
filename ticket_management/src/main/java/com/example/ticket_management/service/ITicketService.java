package com.example.ticket_management.service;

import com.example.ticket_management.dto.ITicketDTO1;
import com.example.ticket_management.dto.ITicketDto;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService extends IGenerationService<Ticket> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    boolean checkStatusTicket(Integer i);

    Page<ITicketDTO1> findAllByIdCRI(Integer idCRI, Pageable pageable);

    Page<ITicketDto> findAllTicketInformationOfUser(Pageable pageable, String email);

    void setTicketByIsDelete();

    Integer findAllTicketByCiIdAndStatus(Integer id, int i);

    List<Ticket> findAllTicketByCRI(Integer idCRI);

    ITicketDTO1 getITicketDTO1ById(Integer id);

    void saveAll(List<Ticket> ticketList);

    void updateTicketStatus(List<Ticket> tickets);
}
