package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public interface ITicketDto {
     Integer getNumberSeat();

     Long getPrice();

     String getName();

     LocalDateTime getStartTime();

     LocalDateTime getEndTime();

     String getLicensePlates();

     String getStartingPoint();

     String getEndingPoint();
}
