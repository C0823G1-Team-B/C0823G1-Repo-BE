package com.example.ticket_management.dto;

public interface ITicketDTO {
    Integer getId();

    Integer getNumberSeat();

    Boolean getStatus();

    String getCustomerName();

    Boolean getPaymentStatus();

    Long getPrice();
    String getStartingPoint();
    String getEndingPoint();
}
