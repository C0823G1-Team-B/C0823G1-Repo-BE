package com.example.ticket_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "detail_ticket_id",referencedColumnName = "id")
    private DetailTicket detailTicket;

    public Payment() {
    }

    public Payment(Integer id, boolean status, DetailTicket detailTicket) {
        this.id = id;
        this.status = status;
        this.detailTicket = detailTicket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DetailTicket getDetailTicket() {
        return detailTicket;
    }

    public void setDetailTicket(DetailTicket detailTicket) {
        this.detailTicket = detailTicket;
    }
}
