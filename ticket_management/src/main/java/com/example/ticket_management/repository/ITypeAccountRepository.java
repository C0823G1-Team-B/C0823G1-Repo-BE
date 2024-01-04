package com.example.ticket_management.repository;

import com.example.ticket_management.model.TypeAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeAccountRepository extends JpaRepository<TypeAccount,Integer> {
}
