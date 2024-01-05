package com.example.ticket_management.service;

import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.common.IGenerationService;

public interface ITicketService extends IGenerationService<Ticket> {
    Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual);
}
