package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_ticket")
public class TypeTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @OneToMany(mappedBy = "typeTicket")
    private List<Ticket> tickets;
    public TypeTicket() {
    }

    public TypeTicket(Integer id, String name, boolean isDelete, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
