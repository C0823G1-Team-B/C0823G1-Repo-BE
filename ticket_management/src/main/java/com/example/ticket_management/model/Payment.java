package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "integer default 0")
    private Integer status;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;
    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    private String passCode;

    public Payment() {
    }

    public Payment(Integer id, Integer status, boolean isDelete, List<Ticket> tickets) {
        this.id = id;
        this.status = status;
        this.isDelete = isDelete;
        this.tickets = tickets;
    }

    public Payment(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String code) {
        this.passCode = code;
    }
}
