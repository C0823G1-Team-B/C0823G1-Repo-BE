package com.example.ticket_management.repository.auth;

import com.example.ticket_management.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
}
