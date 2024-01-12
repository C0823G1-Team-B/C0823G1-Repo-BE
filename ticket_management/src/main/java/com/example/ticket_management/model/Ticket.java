package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number_seat")
    private Integer numberSeat;
    private Long price;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean status;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "car_route_individual_id", referencedColumnName = "id")
    @JsonBackReference
    private CarRouteIndividual carRouteIndividual;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonBackReference
    private Customer customers;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonBackReference
    private Payment payments;

    public Ticket() {
    }

    public Ticket(Integer id, Integer numberSeat, Long price, boolean status, boolean isDelete, CarRouteIndividual carRouteIndividual, Customer customers, Payment payments) {
        this.id = id;
        this.numberSeat = numberSeat;
        this.price = price;
        this.status = status;
        this.isDelete = isDelete;
        this.carRouteIndividual = carRouteIndividual;
        this.customers = customers;
        this.payments = payments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public CarRouteIndividual getCarRouteIndividual() {
        return carRouteIndividual;
    }

    public void setCarRouteIndividual(CarRouteIndividual carRouteIndividual) {
        this.carRouteIndividual = carRouteIndividual;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }
}
