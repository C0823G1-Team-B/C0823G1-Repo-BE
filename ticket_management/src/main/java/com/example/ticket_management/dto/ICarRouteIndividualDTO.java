package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public interface ICarRouteIndividualDTO {
    Integer getId();
    LocalDateTime getEndTime();
    LocalDateTime getStartTime();
    Integer getTotalSeats();
    Long getPrice();
    String getEndingPoint();
    String getStartingPoint();
    String getDriverName();
    String getLicensePlates();
    Integer getSold();
    Long getTotalAmount();
}
