package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Account;
import com.example.ticket_management.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
