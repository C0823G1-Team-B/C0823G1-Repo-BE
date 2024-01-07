package com.example.ticket_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Generality {
    @GetMapping("/admin")
    public String showHomeAdmin(Model model) {
        model.addAttribute("a", "a");
        return "admin";
    }
}
