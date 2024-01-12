package com.example.ticket_management.service.impl;

import com.example.ticket_management.config.VNPayConfig;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.repository.IPaymentRepository;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository iPaymentRepository;
    @Autowired
    private ITicketService ticketService;

    @Override
    public Iterable<Payment> findAll() {
        return iPaymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return iPaymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        return iPaymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iPaymentRepository.deleteById(id);
    }

    @Override
    public Payment updatePaymentStatus(Integer paymentId, Integer i) {
        Payment payment = iPaymentRepository.findById(paymentId).orElse(null);
        if (payment != null && !payment.isDelete()) {
            payment.setStatus(i);
            iPaymentRepository.save(payment);
            return payment;
        }
        return payment;
    }

    @Override
    public Payment createPayment(List<Ticket> ticketList, Customer customer) {
        Payment payment = iPaymentRepository.save(new Payment());
        payment.setPassCode(VNPayConfig.getRandomNumber(8));
        payment.setStatus(0);
        iPaymentRepository.save(payment);
        for (Ticket ticket : ticketList) {
            ticket.setPayments(payment);
            ticket.setCustomers(customer);
        }
        ticketService.saveAll(ticketList);
        return payment;
    }
}
