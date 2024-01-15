package com.example.ticket_management.controller.restful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RESTOauth2User {
    @GetMapping("/api/user")
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal OAuth2User principal){
        System.out.println(principal.getName());
        System.out.println(Optional.ofNullable(principal.getAttribute("name")));
        System.out.println(Optional.ofNullable(principal.getAttribute("email")));
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
    }
}
