package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.ITicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements ITicketService {
    @Override
    public Iterable<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
