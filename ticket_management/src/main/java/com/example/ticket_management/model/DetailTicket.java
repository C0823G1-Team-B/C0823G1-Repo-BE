package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detail_ticket")
public class DetailTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "detailTicket")
    private List<Payment> payments;

    public DetailTicket() {
    }

    public DetailTicket(Integer id, boolean isDelete, Ticket ticket, Customer customer, List<Payment> payments) {
        this.id = id;
        this.isDelete = isDelete;
        this.ticket = ticket;
        this.customer = customer;
        this.payments = payments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
