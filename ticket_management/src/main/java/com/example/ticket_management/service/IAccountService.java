package com.example.ticket_management.service;

import com.example.ticket_management.model.auth.Account;

public interface IAccountService {
    Account findByUsername(String username);
}
