package com.example.ticket_management.repository;

import com.example.ticket_management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car,Integer> {
}
