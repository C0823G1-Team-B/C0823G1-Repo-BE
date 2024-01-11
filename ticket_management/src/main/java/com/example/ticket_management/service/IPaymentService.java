package com.example.ticket_management.service;

import com.example.ticket_management.model.Payment;
import com.example.ticket_management.service.common.IGenerationService;

public interface IPaymentService extends IGenerationService<Payment> {
    boolean updatePaymentStatus(Integer paymentId, Integer i);
}
