package com.example.testduan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowHome {

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("a", "a");
        return "home";
    }
}
