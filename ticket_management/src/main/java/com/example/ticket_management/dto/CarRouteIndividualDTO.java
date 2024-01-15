package com.example.ticket_management.dto;

import java.time.LocalDateTime;

public class CarRouteIndividualDTO {
    private Integer idCRI;
    private String startDateTime;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String endDateTime;
    private String route;
    private String car;
    private String driver;
    private Long price;
    private Integer idRoute;
    private Integer idCar;
    private Integer idDriver;
    private boolean ticketExist;

    public CarRouteIndividualDTO(boolean ticketExist) {
        this.ticketExist = ticketExist;
    }

    public boolean isTicketExist() {
        return ticketExist;
    }

    public void setTicketExist(boolean ticketExist) {
        this.ticketExist = ticketExist;
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    public CarRouteIndividualDTO() {
    }

    public CarRouteIndividualDTO(Integer idCRI) {
        this.idCRI = idCRI;
    }

    public Integer getIdCRI() {
        return idCRI;
    }

    public void setIdCRI(Integer idCRI) {
        this.idCRI = idCRI;
    }

    public CarRouteIndividualDTO(Integer id,String startDateTime, String endDateTime, String route, String car, String driver, Long price) {
        this.idCRI = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.route = route;
        this.car = car;
        this.driver = driver;
        this.price = price;
    }
    public CarRouteIndividualDTO(Integer id,LocalDateTime startDateTime, LocalDateTime endDateTime, Integer routeId, Integer carId, Integer driverId, Long price) {
        this.idCRI = id;
        this.timeStart = startDateTime;
        this.timeEnd = endDateTime;
        this.idRoute = routeId;
        this.idCar = carId;
        this.idDriver = driverId;
        this.price = price;
    }
    public CarRouteIndividualDTO(Integer id,LocalDateTime startDateTime, LocalDateTime endDateTime, Integer routeId, Integer carId, Integer driverId, Long price,boolean ticketExist) {
        this.idCRI = id;
        this.timeStart = startDateTime;
        this.timeEnd = endDateTime;
        this.idRoute = routeId;
        this.idCar = carId;
        this.idDriver = driverId;
        this.price = price;
        this.ticketExist = ticketExist;
    }


    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarRouteIndividualDTO{" +
                "idCRI=" + idCRI +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", route='" + route + '\'' +
                ", car='" + car + '\'' +
                ", driver='" + driver + '\'' +
                ", price=" + price +
                '}';
    }
}
