package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.CarRouteIndividual;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ITicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/ticket")
@SessionAttributes("ticketCart")
public class TicketController {
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;

    @ModelAttribute("ticketCart")
    public TicketCart getTicketCart() {
        return new TicketCart();
    }

    @GetMapping("/{idCRI}")
    public ModelAndView showTicket(@PathVariable Integer idCRI) {
        Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
        if (!carRouteIndividual.isPresent()) {
            return new ModelAndView("error");
        }
        ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", new TicketCart());
        modelAndView.addObject("cri", iCarRouteIndividualDTO);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("ctmDTO", new CustomerDTO());
        return modelAndView;
    }

    @GetMapping("/selectticket/{id}")
    public ModelAndView selectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart,
                                     @PathVariable Integer id,
                                     @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
                                     @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                     @RequestParam(value = "email", required = false, defaultValue = "") String email) {
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()) {
            return new ModelAndView("error");
        }
        ticketCart.addTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", ticketCart);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        CustomerDTO customerDTO = new CustomerDTO(email, name, phoneNumber);
        modelAndView.addObject("cri", iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        modelAndView.addObject("ctmDTO", customerDTO);
        return modelAndView;
    }

    @GetMapping("/noselectticket/{id}")
    public ModelAndView noSelectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart, @PathVariable Integer id,
                                       @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
                                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                       @RequestParam(value = "email", required = false, defaultValue = "") String email) {
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()) {
            return new ModelAndView("error");
        }
        ticketCart.removeTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", ticketCart);
        CustomerDTO customerDTO = new CustomerDTO(email, name, phoneNumber);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("cri", iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        modelAndView.addObject("ctmDTO", customerDTO);
        return modelAndView;
    }

    @PostMapping("/payment/{idCRI}")
    public ModelAndView showPayment(@Validated @ModelAttribute("ctmDTO") CustomerDTO customerDTO, BindingResult bindingResult, @PathVariable Integer idCRI, @ModelAttribute("ticketCart") TicketCart ticketCart) {
        if (bindingResult.hasErrors()) {
            Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
            if (!carRouteIndividual.isPresent()) {
                return new ModelAndView("error");
            }
            ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
            Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
            List<Ticket> tickets1 = (List<Ticket>) tickets;
            ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
            modelAndView.addObject("ticketCart", ticketCart);
            modelAndView.addObject("cri", iCarRouteIndividualDTO);
            modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            return modelAndView;
        }

        Map<Integer, Ticket> ticketMap = ticketCart.ticketList;
        Set<Integer> integers = ticketMap.keySet();
        for (Integer i : integers) {
            if (iTicketService.checkStatusTicket(i)) {
                Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
                if (!carRouteIndividual.isPresent()) {
                    return new ModelAndView("error");
                }
                ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
                Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
                List<Ticket> tickets1 = (List<Ticket>) tickets;
                ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
                modelAndView.addObject("ticketCart", new TicketCart());
                modelAndView.addObject("cri", iCarRouteIndividualDTO);
                modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
                modelAndView.addObject("message", "Đã có khách hàng khách khác chọn vé nhanh hơn! Xin mời bạn chọn lại!");
                return modelAndView;
            }
        }
        System.out.println(customerDTO.getPhoneNumber());


        // Đoạn này mọi người xử lý nhé đã có giỏ hàng vé và thông tin khách hàng
        // Mọi người xử lý ok thì lưu vào database và
        // Set lại chỗ vé: status , id_customer, id_payment để vé hiển thị đúng nhé
        // Hiện tại đang để về trang error
        return new ModelAndView("error");
    }
}
