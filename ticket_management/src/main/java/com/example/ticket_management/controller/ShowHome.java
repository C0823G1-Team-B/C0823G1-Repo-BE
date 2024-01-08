package com.example.ticket_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class ShowHome {
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("a", "a");
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        System.out.println(date);
        System.out.println(localDateTime);
        return "home";
    }

//    @GetMapping("/booksTick")

}
