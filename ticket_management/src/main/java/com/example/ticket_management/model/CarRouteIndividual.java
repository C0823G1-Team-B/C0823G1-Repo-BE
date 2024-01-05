package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "car_route_individual")
public class CarRouteIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;

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

    public CarRouteIndividual(Integer id, LocalDateTime startTime, LocalDateTime endTime, boolean isDelete, Car car, Driver driver, CarRoute carRoute, List<Ticket> tickets) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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

    @Override
    public String toString() {
        return "CarRouteIndividual{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isDelete=" + isDelete +
                ", car=" + car +
                ", driver=" + driver +
                ", carRoute=" + carRoute +
                ", tickets=" + tickets +
                '}';
    }
}
