package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String birthday;
    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "customer" )
    private List<DetailTicket> detailTickets;

    public Customer() {
    }

    public Customer(Integer id, String name, String birthday, Account account, List<DetailTicket> detailTickets) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.account = account;
        this.detailTickets = detailTickets;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<DetailTicket> getDetailTickets() {
        return detailTickets;
    }

    public void setDetailTickets(List<DetailTicket> detailTickets) {
        this.detailTickets = detailTickets;
    }
}
