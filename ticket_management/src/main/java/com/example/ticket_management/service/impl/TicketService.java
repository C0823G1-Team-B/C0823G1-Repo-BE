package com.example.ticket_management.service.impl;

import com.example.ticket_management.dto.ITicketDTO1;
import com.example.ticket_management.dto.ITicketDto;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.repository.ICarRouteIndividualRepository;
import com.example.ticket_management.repository.ITicketRepository;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public boolean checkStatusTicket(Integer i) {
        List<Ticket> tickets = (List<Ticket>) findAll();
        for (Ticket ticket:tickets){
            if (Objects.equals(ticket.getId(), i)){
                if (ticket.isStatus()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Page<ITicketDTO1> findAllByIdCRI(Integer idCRI, Pageable pageable) {
        return iTicketRepository.findAllByIdCRI(idCRI, pageable);
    }

    @Override
    public Page<ITicketDto> findAllTicketInformationOfUser(Pageable pageable, String email) {
       Page<ITicketDto> ticketDtos = iTicketRepository.findAllTicketInformationOfUser(pageable, "%" + email + "%");
//        return iTicketRepository.findAllTicketInformationOfUser(pageable, "%" + email + "%");
        return ticketDtos;
    }

    @Override
    public void setTicketByIsDelete() {
        iTicketRepository.setTicketIsDelete();
    }

    @Override
    public ITicketDTO1 getITicketDTO1ById(Integer id) {
        return iTicketRepository.getITicketDTO1ById(id);
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
