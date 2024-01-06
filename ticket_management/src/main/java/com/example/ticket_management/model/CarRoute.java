package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @OneToMany(mappedBy = "carRoute")
    private List<CarRouteIndividual> carRouteIndividualList;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean isDelete;

    public CarRoute() {
    }

    public CarRoute(String startingPoint, String endingPoint, boolean isDelete) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.isDelete = isDelete;
    }

    public CarRoute(String startingPoint, String endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public CarRoute(Integer id, String startingPoint, String endingPoint, List<CarRouteIndividual> carRouteIndividualList, boolean isDelete) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.carRouteIndividualList = carRouteIndividualList;
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

    public List<CarRouteIndividual> getCarRouteIndividualList() {
        return carRouteIndividualList;
    }

    public void setCarRouteIndividualList(List<CarRouteIndividual> carRouteIndividualList) {
        this.carRouteIndividualList = carRouteIndividualList;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
