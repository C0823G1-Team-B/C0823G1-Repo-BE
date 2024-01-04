package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String licensePlates;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @OneToMany(mappedBy = "car")
    private List<CarRouteIndividual> carRouteIndividualList;

    @ManyToOne
    @JoinColumn(name = "type_car_id",referencedColumnName = "id")
    private TypeCar typeCar;

    public Car() {
    }

    public Car(Integer id, String licensePlates, boolean isDelete, List<CarRouteIndividual> carRouteIndividualList, TypeCar typeCar) {
        this.id = id;
        this.licensePlates = licensePlates;
        this.isDelete = isDelete;
        this.carRouteIndividualList = carRouteIndividualList;
        this.typeCar = typeCar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }
}
