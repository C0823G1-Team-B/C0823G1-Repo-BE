package com.example.ticket_management.controller;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/car-route-individual")
public class CarRouteIndividualController {
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;
    @Autowired
    private ITicketService iTicketService;

    @GetMapping("")
    public ModelAndView showHome() {
        Iterable<CarRouteIndividual> carRouteIndividuals = iCarRouteIndividualService.findAll();
        return new ModelAndView("car-route-individual", "criList", carRouteIndividuals);
    }

    @GetMapping("/cri-management")
    public ModelAndView showListCRIAdmin(){
        iTicketService.setTicketByIsDelete();
        return new ModelAndView("cri-list-admin");
    }
}
