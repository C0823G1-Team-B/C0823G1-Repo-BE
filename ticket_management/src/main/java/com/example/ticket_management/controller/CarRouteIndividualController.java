package com.example.ticket_management.controller;

import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.ICarRouteIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/car-route-individual")
public class CarRouteIndividualController {
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;

    @GetMapping("")
    public ModelAndView showHome() {
        Iterable<CarRouteIndividual> carRouteIndividuals = iCarRouteIndividualService.findAll();
        Optional<CarRouteIndividual> carRouteIndividual1 = iCarRouteIndividualService.findById(3);
        Ticket ticket;
        for (int i = 0; i < carRouteIndividual1.get().getCar().getTotalSeats(); i++) {
            ticket = new Ticket();
            ticket.setNumberSeat(i + 1);
        }
        return new ModelAndView("car-route-individual", "criList", carRouteIndividuals);
    }
}
