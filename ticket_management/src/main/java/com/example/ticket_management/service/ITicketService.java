package com.example.ticket_management.service;

import com.example.ticket_management.dto.ITicketDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.common.IGenerationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService extends IGenerationService<Ticket> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);

    boolean checkStatusTicket(Integer i);

    Page<ITicketDTO> findAllByIdCRI(Integer idCRI, Pageable pageable);

    Page<ITicketDto> findAllTicketInformationOfUser(Pageable pageable, String email);

}
