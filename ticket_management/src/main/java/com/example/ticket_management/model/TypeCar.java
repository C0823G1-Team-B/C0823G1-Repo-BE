package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_car")
public class TypeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_car")
    private String typeCar;

    @OneToMany(mappedBy = "typeCar")
    private List<Car> carList;

    public TypeCar() {
    }

    public TypeCar(Integer id, String typeCar, List<Car> carList) {
        this.id = id;
        this.typeCar = typeCar;
        this.carList = carList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String type_car) {
        this.typeCar = type_car;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
