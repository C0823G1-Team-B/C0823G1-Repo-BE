package com.example.ticket_management.service;

import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.common.IGenerationService;

import java.util.List;

public interface IPaymentService extends IGenerationService<Payment> {
    Payment updatePaymentStatus(Integer paymentId, Integer i);

    Payment createPayment(List<Ticket> ticketList, Customer customer);
}
