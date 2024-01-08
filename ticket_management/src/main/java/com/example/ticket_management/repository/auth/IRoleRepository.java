package com.example.ticket_management.repository.auth;

import com.example.ticket_management.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
}
