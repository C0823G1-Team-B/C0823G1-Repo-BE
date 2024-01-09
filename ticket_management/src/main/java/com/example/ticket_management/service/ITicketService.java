package com.example.ticket_management.service;

import com.example.ticket_management.dto.ITicketDto;
import com.example.ticket_management.dto.TicketDto;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService extends IGenerationService<Ticket> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    boolean checkStatusTicket(Integer i);

    Page<ITicketDto> findAllTicketInformationOfUser(Pageable pageable, String email);

}
