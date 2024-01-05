package com.example.ticket_management.controller;

import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket")
@SessionAttributes("ticketCart")
public class TicketController {
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;
    @ModelAttribute("ticketCart")
    public TicketCart getTicketCart(){
        return new TicketCart();
    }
    @GetMapping("/{idCRI}")
    public ModelAndView showTicket(@PathVariable Integer idCRI){

        Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
        if (!carRouteIndividual.isPresent()){
            return new ModelAndView("error");
        }
        ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket","tickets",tickets1);
        modelAndView.addObject("ticketCart",new TicketCart());
        modelAndView.addObject("cri",iCarRouteIndividualDTO);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        return modelAndView;
    }

    @GetMapping("/selectticket/{id}")
    public ModelAndView selectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart, @PathVariable Integer id){
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()){
            return new ModelAndView("error");
        }
        ticketCart.addTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket","tickets",tickets1);
        modelAndView.addObject("ticketCart",ticketCart);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("cri",iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        return modelAndView;
    }

    @GetMapping("/noselectticket/{id}")
    public ModelAndView noSelectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart, @PathVariable Integer id){
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()){
            return new ModelAndView("error");
        }
        ticketCart.removeTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket","tickets",tickets1);
        modelAndView.addObject("ticketCart",ticketCart);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("cri",iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        return modelAndView;
    }
}
