package com.example.ticket_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_car")
public class TypeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @Column(name = "type_car")
    private String typeCar;
    @OneToMany(mappedBy = "typeCar")
    private List<Car> carList;

    public TypeCar() {
    }

    public TypeCar(Integer id, boolean isDelete, String typeCar, List<Car> carList) {
        this.id = id;
        this.isDelete = isDelete;
        this.typeCar = typeCar;
        this.carList = carList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
