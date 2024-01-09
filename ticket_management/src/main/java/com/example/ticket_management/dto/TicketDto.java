package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public class TicketDto {
    //t.number_seat,t.price, c.name,cri.start_time,cri.end_time,car.license_plates, cr.starting_point,cr.ending_point
    private Integer numberSeat;

    private Long price;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String licensePlates;

    private String startingPoint;

    private String endingPoint;

    public TicketDto(Integer numberSeat, Long price, String name, LocalDateTime startTime, LocalDateTime endTime, String licensePlates, String startingPoint, String endingPoint) {
        this.numberSeat = numberSeat;
        this.price = price;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.licensePlates = licensePlates;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public TicketDto() {
    }

    public Integer getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(Integer numberSeat) {
        this.numberSeat = numberSeat;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }
}
