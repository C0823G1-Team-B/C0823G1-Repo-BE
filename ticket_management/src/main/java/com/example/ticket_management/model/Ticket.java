package com.example.ticket_management.model;

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
    @Column(columnDefinition = "int default 0")
    private boolean status;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "type_ticket",referencedColumnName = "id")
    private TypeTicket typeTicket;

    @ManyToOne
    @JoinColumn(name = "car_route_individual_id",referencedColumnName = "id")
    private CarRouteIndividual carRouteIndividual;

    @OneToMany(mappedBy = "ticket")
    private List<DetailTicket> detailTickets;

    public Ticket() {
    }

    public Ticket(Integer id, Integer numberSeat, boolean status, boolean isDelete, TypeTicket typeTicket, CarRouteIndividual carRouteIndividual, List<DetailTicket> detailTickets) {
        this.id = id;
        this.numberSeat = numberSeat;
        this.status = status;
        this.isDelete = isDelete;
        this.typeTicket = typeTicket;
        this.carRouteIndividual = carRouteIndividual;
        this.detailTickets = detailTickets;
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

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public CarRouteIndividual getCarRouteIndividual() {
        return carRouteIndividual;
    }

    public void setCarRouteIndividual(CarRouteIndividual carRouteIndividual) {
        this.carRouteIndividual = carRouteIndividual;
    }

    public List<DetailTicket> getDetailTickets() {
        return detailTickets;
    }

    public void setDetailTickets(List<DetailTicket> detailTickets) {
        this.detailTickets = detailTickets;
    }
}
