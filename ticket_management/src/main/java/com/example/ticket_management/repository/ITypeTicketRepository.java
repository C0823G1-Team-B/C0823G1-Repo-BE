package com.example.ticket_management.repository;

import com.example.ticket_management.model.TypeTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeTicketRepository extends JpaRepository<TypeTicket,Integer> {
}
