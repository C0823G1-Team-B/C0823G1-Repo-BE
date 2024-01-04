package com.example.ticket_management.repository;

import com.example.ticket_management.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDriverRepository extends JpaRepository<Driver,Integer> {
}
