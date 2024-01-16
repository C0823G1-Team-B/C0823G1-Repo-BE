package com.example.ticket_management.controller.restful;

import com.example.ticket_management.model.Customer;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RESTOauth2User {
    @GetMapping("/api/user")
    public ResponseEntity<Customer> getUserInfo(@AuthenticationPrincipal @Nullable OAuth2User principal) {
        if (principal == null) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        String email = principal.getAttribute("email");
        System.out.println(email);
        return new ResponseEntity<>(new Customer(email), HttpStatus.OK);
    }
}
