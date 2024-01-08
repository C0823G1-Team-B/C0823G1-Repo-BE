package com.example.ticket_management.model;

import java.util.List;

public class CarAndDriverDto {
    private List<Driver> driverList;
    private List<Car> carList;

    public CarAndDriverDto() {
    }

    public CarAndDriverDto(List<Driver> driverList, List<Car> carList) {
        this.driverList = driverList;
        this.carList = carList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
