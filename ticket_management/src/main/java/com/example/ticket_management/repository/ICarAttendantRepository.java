package com.example.ticket_management.repository;

import com.example.ticket_management.model.CarAttendant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarAttendantRepository extends JpaRepository<CarAttendant,Integer> {
}
