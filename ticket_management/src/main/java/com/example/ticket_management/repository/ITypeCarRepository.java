package com.example.ticket_management.repository;

import com.example.ticket_management.model.TypeCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeCarRepository extends JpaRepository<TypeCar,Integer> {
}
