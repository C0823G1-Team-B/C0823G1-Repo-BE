package com.example.ticket_management.repository;

import com.example.ticket_management.model.DetailTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetailTicketRepository extends JpaRepository<DetailTicket,Integer> {
}
