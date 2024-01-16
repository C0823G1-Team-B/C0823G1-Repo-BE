package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String phoneNumber;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;

    @OneToMany(mappedBy = "customers")
    private List<Ticket> ticket;

    public Customer() {
    }


    public Customer(Integer id, String email, String name, String phoneNumber, boolean isDelete, List<Ticket> ticket) {

        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDelete = isDelete;
        this.ticket = ticket;
    }

    public Customer(String email, String name, String phoneNumber, List<Ticket> ticket) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.ticket = ticket;
    }

    public Customer(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
}
