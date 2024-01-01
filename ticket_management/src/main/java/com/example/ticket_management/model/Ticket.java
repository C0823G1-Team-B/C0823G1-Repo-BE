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
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "type_ticket",referencedColumnName = "id")
    private TypeTicket typeTicket;

    @ManyToOne
    @JoinColumn(name = "detail_car_id",referencedColumnName = "id")
    private DetailCar detailCar;

    @OneToMany(mappedBy = "ticket")
    private List<DetailTicket> detailTickets;

    public Ticket() {
    }

    public Ticket(Integer id, Integer numberSeat, boolean status, TypeTicket typeTicket, DetailCar detailCar, List<DetailTicket> detailTickets) {
        this.id = id;
        this.numberSeat = numberSeat;
        this.status = status;
        this.typeTicket = typeTicket;
        this.detailCar = detailCar;
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

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public DetailCar getDetailCar() {
        return detailCar;
    }

    public void setDetailCar(DetailCar detailCar) {
        this.detailCar = detailCar;
    }

    public List<DetailTicket> getDetailTickets() {
        return detailTickets;
    }

    public void setDetailTickets(List<DetailTicket> detailTickets) {
        this.detailTickets = detailTickets;
    }
}
