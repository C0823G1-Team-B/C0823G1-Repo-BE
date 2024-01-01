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
    @OneToMany(mappedBy = "car")
    private List<DetailCar> detailCarList;

    @ManyToOne
    @JoinColumn(name = "type_car_id",referencedColumnName = "id")
    private TypeCar typeCar;

    public Car() {
    }

    public Car(Integer id, String licensePlates, List<DetailCar> detailCarList, TypeCar typeCar) {
        this.id = id;
        this.licensePlates = licensePlates;
        this.detailCarList = detailCarList;
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

    public List<DetailCar> getDetailCarList() {
        return detailCarList;
    }

    public void setDetailCarList(List<DetailCar> detailCarList) {
        this.detailCarList = detailCarList;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }
}
