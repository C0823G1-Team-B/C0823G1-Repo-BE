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

    @Column(columnDefinition = "int default 0")
    private boolean isDelete;

    public CarRoute() {
    }

    public CarRoute(Integer id, String startingPoint, String endingPoint, List<DetailCar> detailCarList, boolean isDelete) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.detailCarList = detailCarList;
        this.isDelete = isDelete;
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

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public List<DetailCar> getDetailCarList() {
        return detailCarList;
    }

    public void setDetailCarList(List<DetailCar> detailCarList) {
        this.detailCarList = detailCarList;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
