package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CarRouteDTO;
import com.example.ticket_management.model.CarRoute;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ICarRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShowHome {
    @Autowired
    ICarRouteService iCarRouteService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("a", "a");
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        List<CarRouteDTO> routeDTOS = iCarRouteService.getCarRouteHigh();
        List<CarRoute> routeListHigh = new ArrayList<>();
        for (CarRouteDTO temp : routeDTOS) {
            routeListHigh.add(iCarRouteService.findById(temp.getCarRouteId()).get());
        }
        model.addAttribute("routeCar", routeListHigh);
        return "home";
    }

    //    @GetMapping("/booksTick")
    @GetMapping("/test")
    public String toTest() {
        System.out.println("OKOKKOKOK");
        return "test";
    }
}
