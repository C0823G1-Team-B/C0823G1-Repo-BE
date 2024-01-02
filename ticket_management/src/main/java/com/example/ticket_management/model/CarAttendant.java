package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_attendant")
public class CarAttendant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String birthday;
    private String identity;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @OneToMany(mappedBy = "carAttendant")
    private List<DetailCar> detailCarList;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    public CarAttendant() {
    }

    public CarAttendant(Integer id, String name, String birthday, String identity, boolean isDelete, List<DetailCar> detailCarList, Account account) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.identity = identity;
        this.isDelete = isDelete;
        this.detailCarList = detailCarList;
        this.account = account;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<DetailCar> getDetailCarList() {
        return detailCarList;
    }

    public void setDetailCarList(List<DetailCar> detailCarList) {
        this.detailCarList = detailCarList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
