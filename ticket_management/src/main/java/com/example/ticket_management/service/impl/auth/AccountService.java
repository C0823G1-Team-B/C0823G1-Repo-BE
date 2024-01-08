package com.example.ticket_management.service.impl.auth;

import com.example.ticket_management.model.auth.Account;
import com.example.ticket_management.repository.auth.IAccountRepository;
import com.example.ticket_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepo;
    @Override
    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }
}
