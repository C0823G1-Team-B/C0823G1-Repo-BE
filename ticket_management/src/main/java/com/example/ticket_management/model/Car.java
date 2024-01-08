package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer totalSeats;
    private String licensePlates;
    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;
    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<CarRouteIndividual> carRouteIndividualList;

    public Car() {
    }

    public Car(Integer id, Integer totalSeats, String licensePlates, boolean isDelete, List<CarRouteIndividual> carRouteIndividualList) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.licensePlates = licensePlates;
        this.isDelete = isDelete;
        this.carRouteIndividualList = carRouteIndividualList;
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
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
        return "Car{" +
                "id=" + id +
                ", totalSeats=" + totalSeats +
                ", licensePlates='" + licensePlates + '\'' +
                ", isDelete=" + isDelete +
                ", carRouteIndividualList=" + carRouteIndividualList +
                '}';
    }
}
