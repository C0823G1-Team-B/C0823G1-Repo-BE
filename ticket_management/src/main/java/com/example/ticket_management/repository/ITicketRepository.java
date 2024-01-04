package com.example.ticket_management.repository;

import com.example.ticket_management.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket,Integer> {
}
