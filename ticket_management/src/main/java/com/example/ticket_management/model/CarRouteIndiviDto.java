package com.example.ticket_management.model;



public class CarRouteIndiviDto {
    private String startDateTime;
    private String endDateTime;
    private Integer route;
    private Integer car;
    private Integer driver;

    public CarRouteIndiviDto(String startDateTime, String endDateTime, Integer route, Integer car, Integer driver) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.route = route;
        this.car = car;
        this.driver = driver;
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

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }
}
