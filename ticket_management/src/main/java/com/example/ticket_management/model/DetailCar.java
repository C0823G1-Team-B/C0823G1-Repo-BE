package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detail_car")
public class DetailCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String start_time;
    private String end_time;
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

    @OneToMany(mappedBy = "detailCar")
    private List<Ticket> tickets;

    public DetailCar() {
    }

    public DetailCar(Integer id, String start_time, String end_time, boolean isDelete, CarAttendant carAttendant, Car car, Driver driver, CarRoute carRoute, List<Ticket> tickets) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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
