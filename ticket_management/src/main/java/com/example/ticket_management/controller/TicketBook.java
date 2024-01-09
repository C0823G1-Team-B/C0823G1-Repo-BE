package com.example.ticket_management.controller;

import com.example.ticket_management.model.*;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ICarRouteService;
import com.example.ticket_management.service.ICarService;
import com.example.ticket_management.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/ticket")
public class TicketBook {
    @Autowired
    private ICarRouteService iCarRouteService;
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;
    @Autowired
    private IDriverService iDriverService;
    @Autowired
    private ICarService iCarService;


    @GetMapping()
    public String showHomeSearchTicket() {
        return "searchTicket";
    }

    @ModelAttribute("listCarRoute")
    public List<CarRoute> listCarRoute(Model model){
        List<CarRoute> listCarRoute = (List<CarRoute>) iCarRouteService.findAll();
        model.addAttribute("listCarRoute",listCarRoute);
        return listCarRoute;
    }


    @GetMapping("formCreateRoute")
    public String createRouteIn() {

        return "formCreateRoute";
    }

    @GetMapping("createRoute")
    public String save(@RequestParam("departure") String departure,
                       @RequestParam("destination") String destination,
                       RedirectAttributes redirectAttributes) {

        CarRoute carRoute = iCarRouteService.findCarRouteByStartingPointAndEndingPoint(departure, destination);
        if (carRoute != null) {
            redirectAttributes.addFlashAttribute("message", "Đã tồn tại tuyến này");
        } else {
            iCarRouteService.save(new CarRoute(departure, destination, false));
            redirectAttributes.addFlashAttribute("message", "Thêm thành công");
        }
        return "redirect:/api/ticket/formCreateRoute";
    }

    @GetMapping("formCreateRouteIndividual")
    public String createFormIndividual(Model model) {
        List<CarRoute> listCarRoute = (List<CarRoute>) iCarRouteService.findAll();
        List<Driver> listDriver = (List<Driver>) iDriverService.findAll();
        List<Car> listCar = (List<Car>) iCarService.findAll();

        model.addAttribute("listCarRoute", listCarRoute);
        model.addAttribute("listDriver", listDriver);
        model.addAttribute("listCar", listCar);


        return "showFormRouteIndividual";
    }


}
