package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_route_individual")
public class CarRouteIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startTime;
    private String endTime;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "car_attendant_id",referencedColumnName = "id")
    private CarAttendant carAttendant;

    @ManyToOne
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id",referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_route_id",referencedColumnName = "id")
    private CarRoute carRoute;

    @OneToMany(mappedBy = "carRouteIndividual")
    private List<Ticket> tickets;

    public CarRouteIndividual() {
    }

    public CarRouteIndividual(Integer id, String startTime, String endTime, boolean isDelete, CarAttendant carAttendant, Car car, Driver driver, CarRoute carRoute, List<Ticket> tickets) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
        this.carAttendant = carAttendant;
        this.car = car;
        this.driver = driver;
        this.carRoute = carRoute;
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String start_time) {
        this.startTime = start_time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String end_time) {
        this.endTime = end_time;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public CarAttendant getCarAttendant() {
        return carAttendant;
    }

    public void setCarAttendant(CarAttendant carAttendant) {
        this.carAttendant = carAttendant;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CarRoute getCarRoute() {
        return carRoute;
    }

    public void setCarRoute(CarRoute carRoute) {
        this.carRoute = carRoute;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
