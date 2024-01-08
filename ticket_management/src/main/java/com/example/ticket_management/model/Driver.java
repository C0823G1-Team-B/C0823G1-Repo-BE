package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phoneNumber;
    private String identity;
    private String address;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;

    @OneToMany(mappedBy = "driver")
    @JsonBackReference
    private List<CarRouteIndividual> carRouteIndividualList;

    public Driver() {
    }

    public Driver(Integer id) {
        this.id = id;
    }

    public Driver(Integer id, String name, String phoneNumber, String identity, String address, boolean isDelete, List<CarRouteIndividual> carRouteIndividualList) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identity = identity;
        this.address = address;
        this.isDelete = isDelete;
        this.carRouteIndividualList = carRouteIndividualList;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<CarRouteIndividual> getCarRouteIndividualList() {
        return carRouteIndividualList;
    }

    public void setCarRouteIndividualList(List<CarRouteIndividual> carRouteIndividualList) {
        this.carRouteIndividualList = carRouteIndividualList;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identity='" + identity + '\'' +
                ", address='" + address + '\'' +
                ", isDelete=" + isDelete +
                ", carRouteIndividualList=" + carRouteIndividualList +
                '}';
    }
}
