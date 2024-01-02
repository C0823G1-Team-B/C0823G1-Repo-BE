package com.example.ticket_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int default 0")
    private boolean status;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "detail_ticket_id",referencedColumnName = "id")
    private DetailTicket detailTicket;

    public Payment() {
    }

    public Payment(Integer id, boolean status, boolean isDelete, DetailTicket detailTicket) {
        this.id = id;
        this.status = status;
        this.isDelete = isDelete;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public DetailTicket getDetailTicket() {
        return detailTicket;
    }

    public void setDetailTicket(DetailTicket detailTicket) {
        this.detailTicket = detailTicket;
    }
}
