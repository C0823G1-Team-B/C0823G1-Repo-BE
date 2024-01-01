package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_route")
public class CarRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "starting_point")
    private String startingPoint;
    @Column(name = "ending_point")
    private String endingPoint;
    @OneToMany(mappedBy = "carRoute")
    private List<DetailCar> detailCarList;

    public CarRoute() {
    }

    public CarRoute(Integer id, String startingPoint, String endingPoint, List<DetailCar> detailCarList) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.detailCarList = detailCarList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingLocation) {
        this.startingPoint = startingLocation;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingLocation) {
        this.endingPoint = endingLocation;
    }

    public List<DetailCar> getDetailCarList() {
        return detailCarList;
    }

    public void setDetailCarList(List<DetailCar> detailCarList) {
        this.detailCarList = detailCarList;
    }
}
