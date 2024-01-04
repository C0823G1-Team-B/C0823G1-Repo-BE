package com.example.ticket_management.service.impl;

import com.example.ticket_management.service.IDetailTicketService;
import org.springframework.stereotype.Service;

@Service
public class DetailTicket implements IDetailTicketService {
    @Override
    public Iterable<com.example.ticket_management.model.DetailTicket> findAll() {
        return null;
    }

    @Override
    public com.example.ticket_management.model.DetailTicket save(com.example.ticket_management.model.DetailTicket detailTicket) {
        return null;
    }

    @Override
    public com.example.ticket_management.model.DetailTicket findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
