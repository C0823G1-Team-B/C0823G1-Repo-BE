package com.example.ticket_management.repository;

import com.example.ticket_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findCustomerByEmail(String email);


}
