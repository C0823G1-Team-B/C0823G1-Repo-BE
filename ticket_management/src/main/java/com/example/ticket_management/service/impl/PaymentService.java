package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Payment;
import com.example.ticket_management.repository.IPaymentRepository;
import com.example.ticket_management.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository iPaymentRepository;

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
    public boolean updatePaymentStatus(Integer paymentId, Integer i) {
        Payment payment = iPaymentRepository.findById(paymentId).orElse(null);
        if (payment != null && !payment.isDelete()) {
            payment.setStatus(i);
            iPaymentRepository.save(payment);
            return true;
        }
        return false;
    }
}
