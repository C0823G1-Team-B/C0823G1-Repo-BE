package com.example.ticket_management.service.impl;

import com.example.ticket_management.model.Account;
import com.example.ticket_management.repository.IAccountRepository;
import com.example.ticket_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Override
    public Iterable<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iAccountRepository.deleteById(id);
    }
}
