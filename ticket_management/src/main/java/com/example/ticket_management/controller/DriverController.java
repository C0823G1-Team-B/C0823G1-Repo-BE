package com.example.ticket_management.controller;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/driver")
public class DriverController {
    @Autowired
    private IDriverService iDriverService;

    @GetMapping
    public String formDriver(Model model) {
        model.addAttribute("driver", new Driver());
        return "/create";
    }

    @PostMapping("/create")
    public String create(Driver driver){
        this.iDriverService.save(driver);
        System.out.println("ok");
        return "redirect:/admin/driver";
    }



}
