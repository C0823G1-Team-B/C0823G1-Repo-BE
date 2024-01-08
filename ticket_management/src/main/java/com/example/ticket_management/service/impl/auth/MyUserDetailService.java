package com.example.ticket_management.service.impl.auth;

import com.example.ticket_management.model.auth.Account;
import com.example.ticket_management.model.auth.MyUserDetail;
import com.example.ticket_management.repository.auth.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Username " + username + "không tồn tại");
        }
        return new MyUserDetail(account);
    }
}
