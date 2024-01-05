package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public interface ICarRouteIndividualDTO {
    LocalDateTime getEndTime();
    LocalDateTime getStartTime();
    Integer getTotalSeats();
    Long getPrice();
    String getEndingPoint();
    String getStartingPoint();
}
