package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Payment;
import com.example.ticket_management.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Override
    public Iterable<Payment> findAll() {
        return null;
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public Payment findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
