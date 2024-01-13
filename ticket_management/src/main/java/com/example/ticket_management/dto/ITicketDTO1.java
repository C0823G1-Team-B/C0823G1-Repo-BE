package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public interface ITicketDTO1 {
    Integer getId();

    Integer getNumberSeat();

    Boolean getStatus();

    String getCustomerName();

    Integer getPaymentStatus();

    Long getPrice();
    String getStartingPoint();
    String getEndingPoint();

    boolean getIsDelete();
    LocalDateTime getEndTime();
    LocalDateTime getStartTime();
}
