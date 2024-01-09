package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JsonBackReference

    @JoinColumn(name = "driver_id",referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @JsonBackReference

    @JoinColumn(name = "car_route_id",referencedColumnName = "id")
    private CarRoute carRoute;

    @OneToMany(mappedBy = "carRouteIndividual")
    @JsonBackReference

    private List<Ticket> tickets;

    private Long price;

    public CarRouteIndividual() {
    }

    public CarRouteIndividual(Integer id, LocalDateTime startTime, LocalDateTime endTime, boolean isDelete, Car car, Driver driver, CarRoute carRoute, List<Ticket> tickets, Long price) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
        this.car = car;
        this.driver = driver;
        this.carRoute = carRoute;
        this.tickets = tickets;
        this.price = price;
    }

    public CarRouteIndividual(LocalDateTime startTime, LocalDateTime endTime, boolean isDelete, Car car, Driver driver, CarRoute carRoute,Long price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
        this.car = car;
        this.driver = driver;
        this.carRoute = carRoute;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
