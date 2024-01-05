package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.repository.ITicketRepository;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements ITicketService{
    @Autowired
    private ITicketRepository iTicketRepository;

    @Override
    public Iterable<Ticket> findAllByCarRouteIndividual(CarRouteIndividual carRouteIndividual) {
        return iTicketRepository.findAllByCarRouteIndividual(carRouteIndividual);
    }

    @Override
    public Iterable<Ticket> findAll() {
        return iTicketRepository.findAll();
    }

    @Override
    public Ticket save(Ticket ticket) {
        return iTicketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        return iTicketRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iTicketRepository.deleteById(id);
    }
}
